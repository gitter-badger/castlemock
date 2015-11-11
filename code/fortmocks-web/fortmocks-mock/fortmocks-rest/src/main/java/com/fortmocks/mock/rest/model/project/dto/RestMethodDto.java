/*
 * Copyright 2015 Karl Dahlgren
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

package com.fortmocks.mock.rest.model.project.dto;

import com.fortmocks.core.model.event.Event;
import com.fortmocks.core.model.event.dto.EventDto;
import com.fortmocks.mock.rest.model.event.dto.RestEventDto;
import com.fortmocks.mock.rest.model.project.RestMethodStatus;
import com.fortmocks.mock.rest.model.project.RestMethodType;
import com.fortmocks.mock.rest.model.project.RestResponseStrategy;
import org.dozer.Mapping;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Karl Dahlgren
 * @since 1.0
 */

public class RestMethodDto {

    @Mapping("id")
    private Long id;

    @Mapping("name")
    private String name;

    @Mapping("defaultBody")
    private String defaultBody;

    @Mapping("restMethodType")
    private RestMethodType restMethodType;

    @Mapping("forwardedEndpoint")
    private String forwardedEndpoint;

    @Mapping("restMethodStatus")
    private RestMethodStatus restMethodStatus;

    @Mapping("restResponseStrategy")
    private RestResponseStrategy restResponseStrategy;

    @Mapping("restMockResponses")
    private List<RestMockResponseDto> restMockResponses = new LinkedList<RestMockResponseDto>();

    @Mapping("events")
    private List<RestEventDto> events;

    @Mapping("currentResponseSequenceIndex")
    private Integer currentResponseSequenceIndex = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultBody() {
        return defaultBody;
    }

    public void setDefaultBody(String defaultBody) {
        this.defaultBody = defaultBody;
    }

    public RestMethodType getRestMethodType() {
        return restMethodType;
    }

    public void setRestMethodType(RestMethodType restMethodType) {
        this.restMethodType = restMethodType;
    }

    public List<RestMockResponseDto> getRestMockResponses() {
        return restMockResponses;
    }

    public void setRestMockResponses(List<RestMockResponseDto> restMockResponses) {
        this.restMockResponses = restMockResponses;
    }

    public String getForwardedEndpoint() {
        return forwardedEndpoint;
    }

    public void setForwardedEndpoint(String forwardedEndpoint) {
        this.forwardedEndpoint = forwardedEndpoint;
    }

    public RestMethodStatus getRestMethodStatus() {
        return restMethodStatus;
    }

    public void setRestMethodStatus(RestMethodStatus restMethodStatus) {
        this.restMethodStatus = restMethodStatus;
    }

    public RestResponseStrategy getRestResponseStrategy() {
        return restResponseStrategy;
    }

    public void setRestResponseStrategy(RestResponseStrategy restResponseStrategy) {
        this.restResponseStrategy = restResponseStrategy;
    }

    public List<RestEventDto> getEvents() {
        return events;
    }

    public void setEvents(List<RestEventDto> events) {
        this.events = events;
    }

    public Integer getCurrentResponseSequenceIndex() {
        return currentResponseSequenceIndex;
    }

    public void setCurrentResponseSequenceIndex(Integer currentResponseSequenceIndex) {
        this.currentResponseSequenceIndex = currentResponseSequenceIndex;
    }
}