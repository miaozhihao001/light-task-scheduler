package com.lts.example.spring.jobrunner;

import com.lts.core.domain.Action;
import com.lts.core.domain.Job;
import com.lts.core.logger.Logger;
import com.lts.core.logger.LoggerFactory;
import com.lts.example.support.SpringBean;
import com.lts.spring.tasktracker.LTS;
import com.lts.spring.tasktracker.JobRunnerItem;
import com.lts.tasktracker.Result;
import com.lts.tasktracker.logger.BizLogger;
import com.lts.tasktracker.runner.LtsLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Robert HG (254963746@qq.com) on 10/20/15.
 */
@LTS
public class JobScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobScheduler.class);

    @Autowired
    SpringBean springBean;

    @JobRunnerItem(shardValue = "111")
    public Result runJob1(Job job) throws Throwable {
        try {
            Thread.sleep(1000L);

            springBean.hello();

            LOGGER.info("runJob1 我要执行：" + job);
            BizLogger bizLogger = LtsLoggerFactory.getBizLogger();
            // 会发送到 LTS (JobTracker上)
            bizLogger.info("测试，业务日志啊啊啊啊啊");

        } catch (Exception e) {
            LOGGER.info("Run job failed!", e);
            return new Result(Action.EXECUTE_LATER, e.getMessage());
        }
        return new Result(Action.EXECUTE_SUCCESS, "执行成功了，哈哈");
    }

    @JobRunnerItem(shardValue = "222")
    public Result runJob2() throws Throwable {
        try {
            springBean.hello();

            LOGGER.info("runJob2 我要执行");
            BizLogger bizLogger = LtsLoggerFactory.getBizLogger();
            // 会发送到 LTS (JobTracker上)
            bizLogger.info("测试，业务日志啊啊啊啊啊");
        } catch (Exception e) {
            LOGGER.info("Run job failed!", e);
            return new Result(Action.EXECUTE_LATER, e.getMessage());
        }
        return new Result(Action.EXECUTE_SUCCESS, "执行成功了，哈哈");
    }

}
