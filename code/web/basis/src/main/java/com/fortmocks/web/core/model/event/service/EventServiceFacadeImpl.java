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

package com.fortmocks.web.core.model.event.service;

import com.fortmocks.core.basis.model.TypeIdentifiable;
import com.fortmocks.core.basis.model.TypeIdentifier;
import com.fortmocks.core.basis.model.event.domain.Event;
import com.fortmocks.core.basis.model.event.dto.EventDto;
import com.fortmocks.core.basis.model.event.service.EventServiceAdapter;
import com.fortmocks.core.basis.model.event.service.EventServiceFacade;
import com.fortmocks.web.core.model.ServiceFacadeImpl;
import org.springframework.stereotype.Service;

/**
 * The Event service component is used to assembly all the events service layers and interact with them
 * in order to retrieve a unified answer independent of the event type.
 * @author Karl Dahlgren
 * @since 1.0
 * @see Event
 * @see EventDto
 */
@Service
public class EventServiceFacadeImpl extends ServiceFacadeImpl<EventDto, Long, EventServiceAdapter<EventDto>> implements EventServiceFacade {

    /**
     * The initiate method is responsible for for locating all the service instances for a specific module
     * and organizing them depending on the type.
     * @see com.fortmocks.core.basis.model.Service
     * @see TypeIdentifier
     * @see TypeIdentifiable
     */
    @Override
    public void initiate(){
        initiate(EventServiceAdapter.class);
    }
}