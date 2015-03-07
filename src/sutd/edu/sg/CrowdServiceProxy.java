package sutd.edu.sg;

public class CrowdServiceProxy implements sutd.edu.sg.CrowdService {
	private String _endpoint = null;
	private sutd.edu.sg.CrowdService crowdService = null;

	public CrowdServiceProxy() {
		_initCrowdServiceProxy();
	}

	public CrowdServiceProxy(String endpoint) {
		_endpoint = endpoint;
		_initCrowdServiceProxy();
	}

	private void _initCrowdServiceProxy() {
		try {
			crowdService = (new ServiceBehavior.CrowdServiceImplementLocator())
					.getBasicHttpBinding_CrowdService();
			if (crowdService != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) crowdService)
							._setProperty(
									"javax.xml.rpc.service.endpoint.address",
									_endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) crowdService)
							._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (crowdService != null)
			((javax.xml.rpc.Stub) crowdService)._setProperty(
					"javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public sutd.edu.sg.CrowdService getCrowdService() {
		if (crowdService == null)
			_initCrowdServiceProxy();
		return crowdService;
	}

	public String getData(Integer value)
			throws java.rmi.RemoteException {
		if (crowdService == null)
			_initCrowdServiceProxy();
		return crowdService.getData(value);
	}

	public sutd.edu.sg.CrowdOptimizationResult globalOptimize(
			String xml,
			Long globalTimeConstraint,
			Double globalCostConstraint,
			com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9[] workers,
			com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringintKeyValueOfstringint[] resultNums,
			Integer numOfGeneration) throws java.rmi.RemoteException {
		if (crowdService == null)
			_initCrowdServiceProxy();
		return crowdService.globalOptimize(xml, globalTimeConstraint,
				globalCostConstraint, workers, resultNums, numOfGeneration);
	}

}