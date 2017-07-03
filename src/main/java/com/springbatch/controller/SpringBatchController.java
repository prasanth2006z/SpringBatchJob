package com.springbatch.controller;

import com.springbatch.service.BatchJobService;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by prasanth.p on 01/07/17.
 */
@RestController
public class SpringBatchController {

    @Autowired
    private BatchJobService batchJobService;

    @RequestMapping(value = "/run", method = RequestMethod.GET)
    @ResponseBody
    public String run() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        batchJobService.run();
        return "Job Completed!!!";
    }

}
