package gt.com.tigo.workeragreementrenewmanagement.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.com.tigo.workeragreementrenewmanagement.commons.util.ApiConstant;
import gt.com.tigo.workeragreementrenewmanagement.config.ApplicationPropConfig;
import gt.com.tigo.workeragreementrenewmanagement.config.TblInitDBConfiguration;
import gt.com.tigo.workeragreementrenewmanagement.rest.WorkerBusinessController;
import gt.com.tigo.workeragreementrenewmanagement.thread.ControllerWorkerThread;

@Service
public class WorkerReaderConfServiceImpl implements WorkerReaderConfService {

	private final Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	private ApplicationPropConfig applicationPropConfig;

	@Autowired
	private TblInitDBConfiguration tblInitConfig;

	@Autowired
	private WorkerBusinessController workerBusinessController;
	
	@Autowired
	private ContractService contractService;

	boolean processStarted = true;

	@Override
	public boolean stopProcess() {
		return true;
	}

	@Override
	public void startProcess() {

		LOGGER.info("WorkerAgreementRenewal - process Started");

		List<Callable<Boolean>> tasks = new ArrayList<Callable<Boolean>>();

		ControllerWorkerThread rabbitMQThread = new ControllerWorkerThread(applicationPropConfig, tblInitConfig, true,
				workerBusinessController,ApiConstant.READ_QUEUE,contractService);
		ControllerWorkerThread retryContractThread = new ControllerWorkerThread(applicationPropConfig, tblInitConfig,
				true, workerBusinessController,ApiConstant.RETRY,contractService);

		tasks.add(rabbitMQThread);
		tasks.add(retryContractThread);

		ExecutorService executor = Executors.newFixedThreadPool(2);

		try {

			List<Future<Boolean>> futures = executor.invokeAll(tasks);
			for (Future<Boolean> future : futures) {
				Boolean resultado = future.get();
				LOGGER.info("hilo finalizado:: " + future.toString() + "::" + resultado);
			}

		} catch (InterruptedException e) {
			LOGGER.error(e);
		} catch (ExecutionException e) {
			LOGGER.error(e);
		}

		LOGGER.info("WorkerAgreementRenewal - process Finished");

	}

	public boolean isProcessStarted() {
		return processStarted;
	}

	public void setProcessStarted(boolean processStarted) {
		this.processStarted = processStarted;
	}

}
