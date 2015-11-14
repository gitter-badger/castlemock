package com.fortmocks.mock.soap.model.project.service.adapter;

import com.fortmocks.core.model.TypeIdentifier;
import com.fortmocks.core.model.project.dto.ProjectDto;
import com.fortmocks.core.model.project.service.ProjectServiceAdapter;
import com.fortmocks.mock.soap.model.project.dto.SoapProjectDto;
import com.fortmocks.mock.soap.model.project.service.message.input.*;
import com.fortmocks.mock.soap.model.project.service.message.output.CreateSoapProjectOutput;
import com.fortmocks.mock.soap.model.project.service.message.output.ReadAllSoapProjectsOutput;
import com.fortmocks.mock.soap.model.project.service.message.output.ReadSoapProjectOutput;
import com.fortmocks.mock.soap.model.project.service.message.output.UpdateSoapProjectOutput;
import com.fortmocks.web.core.service.ServiceProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Karl Dahlgren
 * @since 1.0
 */
@Service
public class SoapProjectServiceAdapter implements ProjectServiceAdapter<SoapProjectDto> {

    @Autowired
    private ServiceProcessor serviceProcessor;

    @Override
    public SoapProjectDto create(SoapProjectDto dto) {
        final CreateSoapProjectOutput output = serviceProcessor.process(new CreateSoapProjectInput(dto));
        return output.getSavedSoapProject();
    }

    @Override
    public void delete(Long id) {
        serviceProcessor.process(new DeleteSoapProjectInput(id));
    }

    @Override
    public SoapProjectDto update(Long id, SoapProjectDto dto) {
        final UpdateSoapProjectOutput output = serviceProcessor.process(new UpdateSoapProjectInput(id, dto));
        return output.getUpdatedSoapProject();
    }

    @Override
    public List<SoapProjectDto> readAll() {
        final ReadAllSoapProjectsOutput output = serviceProcessor.process(new ReadAllSoapProjectsInput());
        return output.getSoapProjects();
    }

    @Override
    public SoapProjectDto read(Long id) {
        final ReadSoapProjectOutput output = serviceProcessor.process(new ReadSoapProjectInput(id));
        return output.getSoapProject();
    }

    @Override
    public TypeIdentifier getTypeIdentifier() {
        return null;
    }

    @Override
    public SoapProjectDto convertType(ProjectDto parent) {
        return null;
    }

    @Override
    public String exportProject(Long id) {
        return null;
    }

    @Override
    public void importProject(String projectRaw) {

    }
}