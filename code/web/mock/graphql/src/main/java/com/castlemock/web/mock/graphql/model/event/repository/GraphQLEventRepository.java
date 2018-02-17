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

package com.castlemock.web.mock.graphql.model.event.repository;

import com.castlemock.core.basis.model.Repository;
import com.castlemock.core.mock.graphql.model.event.domain.GraphQLEvent;
import com.castlemock.core.mock.graphql.model.event.dto.GraphQLEventDto;

import java.util.List;

public interface GraphQLEventRepository extends Repository<GraphQLEvent, GraphQLEventDto, String> {

    /**
     * The events for a specific operation id
     * @param operationId The id of the operation that the event belongs to
     * @return Returns a list of events
     */
    List<GraphQLEventDto> findEventsByOperationId(String operationId);

    /**
     * The service finds the oldest event
     * @return The oldest event
     */
    GraphQLEventDto getOldestEvent();

    /**
     * The method finds and deletes the oldest event.
     * @return The event that was deleted.
     */
    GraphQLEventDto deleteOldestEvent();

    /**
     * The method clears and deletes all logs.
     */
    void clearAll();

}