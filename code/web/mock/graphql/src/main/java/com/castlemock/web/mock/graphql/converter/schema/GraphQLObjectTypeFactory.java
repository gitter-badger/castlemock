/*
 * Copyright 2018 Karl Dahlgren
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.castlemock.web.mock.graphql.converter.schema;

import com.castlemock.core.mock.graphql.model.project.domain.GraphQLAttributeType;
import com.castlemock.core.mock.graphql.model.project.domain.GraphQLResponseStrategy;
import com.castlemock.core.mock.graphql.model.project.dto.*;
import graphql.language.EnumValueDefinition;
import graphql.schema.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GraphQLObjectTypeFactory {

    private static final String TYPE_ID = "ID";
    private static final String TYPE_STRING = "String";
    private static final String TYPE_INT = "Int";
    private static final String TYPE_FLOAT = "Float";
    private static final String TYPE_BOOLEAN = "Boolean";
    private static final Set<String> REFUSED_OBJECT_TYPES = new HashSet<String>(Arrays.asList(
            "Query", "ID", "String", "Int", "__Schema",
            "__Type", "__TypeKind", "__Field", "__InputValue",
            "Boolean", "__EnumValue", "__Directive", "__DirectiveLocation"));


    public static GraphQLMutationDto mutation(GraphQLFieldDefinition definition){
        GraphQLMutationDto mutation = new GraphQLMutationDto();
        mutation.setName(definition.getName());
        mutation.setResponseStrategy(GraphQLResponseStrategy.RANDOM);

        for(GraphQLArgument argument : definition.getArguments()){
            GraphQLArgumentDto graphQLArgument = getArgument(argument);
            mutation.getArguments().add(graphQLArgument);
        }

        return mutation;
    }

    public static GraphQLQueryDto query(GraphQLFieldDefinition definition){
        GraphQLQueryDto query = new GraphQLQueryDto();
        query.setName(definition.getName());
        query.setResponseStrategy(GraphQLResponseStrategy.RANDOM);

        for(GraphQLArgument argument : definition.getArguments()){
            GraphQLArgumentDto graphQLArgument = getArgument(argument);
            query.getArguments().add(graphQLArgument);
        }

        return query;
    }

    public static GraphQLSubscriptionDto subscription(GraphQLFieldDefinition definition){
        GraphQLSubscriptionDto subscription = new GraphQLSubscriptionDto();
        subscription.setName(definition.getName());
        subscription.setResponseStrategy(GraphQLResponseStrategy.RANDOM);

        for(GraphQLArgument argument : definition.getArguments()){
            GraphQLArgumentDto graphQLArgument = getArgument(argument);
            subscription.getArguments().add(graphQLArgument);
        }

        return subscription;
    }

    public static GraphQLTypeDto parse(graphql.schema.GraphQLType graphQLType){

        if(graphQLType instanceof graphql.schema.GraphQLObjectType) {
            graphql.schema.GraphQLObjectType type =
                    (graphql.schema.GraphQLObjectType) graphQLType;
            return getObjectType(type);
        } else if(graphQLType instanceof graphql.schema.GraphQLEnumType) {
            graphql.schema.GraphQLEnumType type =
                    (graphql.schema.GraphQLEnumType) graphQLType;
            return getEnum(type);
        }



        return null;
    }


    private static GraphQLObjectTypeDto getObjectType(final graphql.schema.GraphQLObjectType type){
        final String name = type.getName();
        if(REFUSED_OBJECT_TYPES.contains(name)){
            return null;
        }

        final GraphQLObjectTypeDto graphQLObjectType = new GraphQLObjectTypeDto();
        graphQLObjectType.setName(name);

        for(GraphQLFieldDefinition definition : type.getFieldDefinitions()){
            GraphQLAttributeDto attribute = getAttribute(definition);
            graphQLObjectType.getAttributes().add(attribute);
        }

        return graphQLObjectType;
    }

    private static GraphQLEnumTypeDto getEnum(final graphql.schema.GraphQLEnumType type){
        final String name = type.getName();
        if(REFUSED_OBJECT_TYPES.contains(name)){
            return null;
        }

        final GraphQLEnumTypeDto graphQLEnumType = new GraphQLEnumTypeDto();
        graphQLEnumType.setName(name);

        for(EnumValueDefinition definition : type.getDefinition().getEnumValueDefinitions()){
            GraphQLEnumValueDefinitionDto enumValueDefinition = new GraphQLEnumValueDefinitionDto();
            enumValueDefinition.setName(definition.getName());
            graphQLEnumType.getDefinitions().add(enumValueDefinition);
        }

        return graphQLEnumType;
    }


    private static GraphQLAttributeDto getAttribute(final GraphQLFieldDefinition definition){
        final GraphQLType type = definition.getType();
        final boolean nullable = isNullable(type);
        final boolean listable = isListable(type);
        final GraphQLType coreType = getType(definition.getType());

        final GraphQLAttributeDto attribute = new GraphQLAttributeDto();
        final GraphQLAttributeType attributeType = getAttributeType(coreType);
        attribute.setName(definition.getName());
        attribute.setNullable(nullable);
        attribute.setListable(listable);
        attribute.setAttributeType(attributeType);

        for(GraphQLArgument argument : definition.getArguments()){
            GraphQLArgumentDto graphQLArgument = getArgument(argument);
            attribute.getArguments().add(graphQLArgument);
        }

        if(GraphQLAttributeType.OBJECT_TYPE.equals(attributeType)){
            attribute.setObjectType(coreType.getName());
        }

        return attribute;
    }

    private static GraphQLArgumentDto getArgument(final GraphQLArgument argument){
        final GraphQLArgumentDto graphQLArgument = new GraphQLArgumentDto();
        final GraphQLType type = argument.getType();
        final boolean nullable = isNullable(type);
        final boolean listable = isListable(type);
        final GraphQLType coreType = getType(argument.getType());
        final GraphQLAttributeType attributeType = getAttributeType(coreType);

        graphQLArgument.setName(argument.getName());
        graphQLArgument.setNullable(nullable);
        graphQLArgument.setListable(listable);
        graphQLArgument.setAttributeType(attributeType);
        graphQLArgument.setDefaultValue(argument.getDefaultValue());

        if(GraphQLAttributeType.OBJECT_TYPE.equals(attributeType)){
            graphQLArgument.setObjectType(coreType.getName());
        }

        return graphQLArgument;
    }

    private static GraphQLAttributeType getAttributeType(GraphQLType type){
        type = getType(type);

        final String name = type.getName();
        if(type instanceof GraphQLScalarType){
            if(TYPE_ID.equalsIgnoreCase(name)){
                return GraphQLAttributeType.ID;
            } else if(TYPE_STRING.equalsIgnoreCase(name)){
                return GraphQLAttributeType.STRING;
            } else if(TYPE_INT.equalsIgnoreCase(name)){
                return GraphQLAttributeType.INT;
            } else if(TYPE_FLOAT.equalsIgnoreCase(name)){
                return GraphQLAttributeType.FLOAT;
            } else if(TYPE_BOOLEAN.equalsIgnoreCase(name)){
                return GraphQLAttributeType.BOOLEAN;
            }
        } else if(type instanceof graphql.schema.GraphQLObjectType){
            return GraphQLAttributeType.OBJECT_TYPE;
        } else if(type instanceof graphql.schema.GraphQLEnumType){
            return GraphQLAttributeType.ENUM;
        }

        throw new IllegalArgumentException("Unable to parse the following attribute: " + name);
    }


    private static boolean isNullable(final GraphQLType type){
        if(type instanceof GraphQLList){
            GraphQLType wrappedType = ((GraphQLList) type).getWrappedType();
            return isNullable(wrappedType);
        }

        return !(type instanceof GraphQLNonNull);
    }

    private static boolean isListable(final GraphQLType type){
        if(type instanceof GraphQLNonNull){
            GraphQLType wrappedType = ((GraphQLNonNull) type).getWrappedType();
            return isListable(wrappedType);
        }

        return (type instanceof GraphQLList);
    }

    private static GraphQLType getType(final GraphQLType type){
        if(type instanceof GraphQLNonNull){
            GraphQLType wrappedType = ((GraphQLNonNull) type).getWrappedType();
            return getType(wrappedType);
        }
        if(type instanceof GraphQLList){
            GraphQLType wrappedType = ((GraphQLList) type).getWrappedType();
            return getType(wrappedType);
        }

        return type;
    }


}