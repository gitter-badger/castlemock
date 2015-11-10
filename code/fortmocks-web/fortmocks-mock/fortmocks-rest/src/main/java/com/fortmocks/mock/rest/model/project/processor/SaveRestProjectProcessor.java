package com.fortmocks.mock.rest.model.project.processor;

import com.fortmocks.core.model.Processor;
import com.fortmocks.core.model.Result;
import com.fortmocks.core.model.Task;
import com.fortmocks.core.model.user.dto.UserDto;
import com.fortmocks.core.model.user.message.SaveUserInput;
import com.fortmocks.core.model.user.message.SaveUserOutput;
import com.fortmocks.mock.rest.model.project.RestProject;
import com.fortmocks.mock.rest.model.project.dto.RestProjectDto;
import com.fortmocks.mock.rest.model.project.message.SaveRestProjectInput;
import com.fortmocks.mock.rest.model.project.message.SaveRestProjectOutput;
import org.springframework.stereotype.Service;

/**
 * @author Karl Dahlgren
 * @since 1.0
 */
@Service
public class SaveRestProjectProcessor extends AbstractRestProjectProcessor implements Processor<SaveRestProjectInput, SaveRestProjectOutput> {

    /**
     * The process message is responsible for processing an incoming task and generate
     * a response based on the incoming task input
     * @param task The task that will be processed by the processor
     * @return A result based on the processed incoming task
     * @see Task
     * @see Result
     */
    @Override
    public Result<SaveRestProjectOutput> process(final Task<SaveRestProjectInput> task) {
        final SaveRestProjectInput input = task.getInput();
        final RestProjectDto restProject = input.getRestProject();
        final RestProjectDto savedRestProject = save(restProject);
        final SaveRestProjectOutput output = new SaveRestProjectOutput();
        output.setSavedRestProject(savedRestProject);
        return createResult(output);
    }
}
