/*
 * Copyright 2016 Karl Dahlgren
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

package com.castlemock.core.mock.websocket.model.project.service.message.input;

import com.castlemock.core.basis.model.Input;
import com.castlemock.core.basis.model.validation.NotNull;
import com.castlemock.core.mock.websocket.model.project.dto.WebSocketBroadcasterDto;
import com.castlemock.core.mock.websocket.model.project.dto.WebSocketResourceDto;

import java.util.List;

/**
 * @author Karl Dahlgren
 * @since 1.6
 */
public class DeleteWebSocketBroadcastersInput implements Input{

    @NotNull
    private String webSocketProjectId;
    @NotNull
    private String webSocketTopicId;
    @NotNull
    private List<WebSocketBroadcasterDto> webSocketBroadcasters;

    public DeleteWebSocketBroadcastersInput(String webSocketProjectId, String webSocketTopicId, List<WebSocketBroadcasterDto> webSocketBroadcasters) {
        this.webSocketProjectId = webSocketProjectId;
        this.webSocketTopicId = webSocketTopicId;
        this.webSocketBroadcasters = webSocketBroadcasters;
    }

    public String getWebSocketProjectId() {
        return webSocketProjectId;
    }

    public void setWebSocketProjectId(String webSocketProjectId) {
        this.webSocketProjectId = webSocketProjectId;
    }

    public String getWebSocketTopicId() {
        return webSocketTopicId;
    }

    public void setWebSocketTopicId(String webSocketTopicId) {
        this.webSocketTopicId = webSocketTopicId;
    }

    public List<WebSocketBroadcasterDto> getWebSocketBroadcasters() {
        return webSocketBroadcasters;
    }

    public void setWebSocketBroadcasters(List<WebSocketBroadcasterDto> webSocketBroadcasters) {
        this.webSocketBroadcasters = webSocketBroadcasters;
    }
}