package org.sysadl.util.builder.pkg;

import org.sysadl.AbstractActionReceive;
import org.sysadl.AbstractActionSend;
import org.sysadl.AbstractActivityDef;
import org.sysadl.AbstractComponentDef;
import org.sysadl.AbstractConnectorDef;
import org.sysadl.AbstractFlow;
import org.sysadl.AbstractPin;
import org.sysadl.AbstractPortUse;
import org.sysadl.AbstractProtocol;
import org.sysadl.AbstractProtocolBody;
import org.sysadl.FlowProperty;
import org.sysadl.Invariant;
import org.sysadl.ProtocolAlternativeType;
import org.sysadl.ProtocolControl;
import org.sysadl.Style;
import org.sysadl.SysADLFactory;

public class StyleIoTBuilder extends ElementBuilder {

	@Override
	public Style build() {
		Style IoTStyle = SysADLFactory.eINSTANCE.createStyle();
		IoTStyle.setName("IoTStyle");

		/*start Components*/
		AbstractComponentDef sensor = SysADLFactory.eINSTANCE.createAbstractComponentDef();
		sensor.setName("SensorCP");
		sensor.setLowerBound(1);
		sensor.setUpperBound(-1);
		AbstractPortUse sensorOPT = SysADLFactory.eINSTANCE.createAbstractPortUse();
		sensorOPT.setName("SensorOPT");
		sensorOPT.setFlowProperty(FlowProperty.OUT);
		sensorOPT.setLowerBound(1);
		sensorOPT.setUpperBound(-1);
		sensor.getPorts().add(sensorOPT);
		
		AbstractComponentDef actuator = SysADLFactory.eINSTANCE.createAbstractComponentDef();
		actuator.setName("ActuatorCP");
		actuator.setLowerBound(0);
		actuator.setUpperBound(-1);
		AbstractPortUse actuatorIPT = SysADLFactory.eINSTANCE.createAbstractPortUse();
		actuatorIPT.setName("ActuatorIPT");
		actuatorIPT.setFlowProperty(FlowProperty.IN);
		actuatorIPT.setLowerBound(1);
		actuatorIPT.setUpperBound(-1);
		actuator.getPorts().add(actuatorIPT);
				
		AbstractComponentDef datastore = SysADLFactory.eINSTANCE.createAbstractComponentDef();
		datastore.setName("DataStoreCP");
		datastore.setLowerBound(1);
		datastore.setUpperBound(-1);
		AbstractPortUse dataOPT = SysADLFactory.eINSTANCE.createAbstractPortUse();
		dataOPT.setName("DataOPT");
		dataOPT.setFlowProperty(FlowProperty.OUT);
		dataOPT.setLowerBound(1);
		dataOPT.setUpperBound(-1);
		datastore.getPorts().add(dataOPT);
		AbstractPortUse dataIPT = SysADLFactory.eINSTANCE.createAbstractPortUse();
		dataIPT.setName("DataIPT");
		dataIPT.setFlowProperty(FlowProperty.IN);
		dataIPT.setLowerBound(1);
		dataIPT.setUpperBound(-1);
		datastore.getPorts().add(dataIPT);
				
		AbstractComponentDef controller = SysADLFactory.eINSTANCE.createAbstractComponentDef();
		controller.setName("ControllerCP");
		controller.setLowerBound(0);
		controller.setUpperBound(-1);
		AbstractPortUse sensorIPT = SysADLFactory.eINSTANCE.createAbstractPortUse();
		sensorIPT.setName("SensorIPT");
		sensorIPT.setFlowProperty(FlowProperty.IN);
		sensorIPT.setLowerBound(0);
		sensorIPT.setUpperBound(-1);
		controller.getPorts().add(sensorIPT);
		AbstractPortUse actuatorOPT = SysADLFactory.eINSTANCE.createAbstractPortUse();
		actuatorOPT.setName("ActuatorOPT");
		actuatorOPT.setFlowProperty(FlowProperty.OUT);
		actuatorOPT.setLowerBound(0);
		actuatorOPT.setUpperBound(-1);
		controller.getPorts().add(actuatorOPT);
		
		AbstractComponentDef gateway = SysADLFactory.eINSTANCE.createAbstractComponentDef();
		gateway.setName("GatewayCP");
		gateway.setLowerBound(0);
		gateway.setUpperBound(-1);
		gateway.getComposition().add(datastore);
		AbstractPortUse deviceIPT = SysADLFactory.eINSTANCE.createAbstractPortUse();
		deviceIPT.setName("DeviceIPT");
		deviceIPT.setFlowProperty(FlowProperty.IN);
		deviceIPT.setLowerBound(1);
		deviceIPT.setUpperBound(-1);
		gateway.getPorts().add(deviceIPT);
		AbstractPortUse serviceOPT = SysADLFactory.eINSTANCE.createAbstractPortUse();
		serviceOPT.setName("ServiceOPT");
		serviceOPT.setFlowProperty(FlowProperty.OUT);
		serviceOPT.setLowerBound(1);
		serviceOPT.setUpperBound(-1);
		gateway.getPorts().add(serviceOPT);
		AbstractPortUse serviceIPT = SysADLFactory.eINSTANCE.createAbstractPortUse();
		serviceIPT.setName("ServiceIPT");
		serviceIPT.setFlowProperty(FlowProperty.IN);
		serviceIPT.setLowerBound(0);
		serviceIPT.setUpperBound(-1);
		gateway.getPorts().add(serviceIPT);
						
		AbstractComponentDef device = SysADLFactory.eINSTANCE.createAbstractComponentDef();
		device.setName("DeviceCP");
		device.setLowerBound(1);
		device.setUpperBound(-1);
		device.getComposition().add(sensor);
		device.getComposition().add(actuator);
		device.getComposition().add(controller);
		device.getComposition().add(gateway);
		device.getComposition().add(datastore);
		AbstractPortUse deviceOPT = SysADLFactory.eINSTANCE.createAbstractPortUse();
		deviceOPT.setName("DeviceOPT");
		deviceOPT.setFlowProperty(FlowProperty.OUT);
		deviceOPT.setLowerBound(0);
		deviceOPT.setUpperBound(-1);
		device.getPorts().add(deviceOPT);
		AbstractPortUse sensorIPTd = SysADLFactory.eINSTANCE.createAbstractPortUse();
		sensorIPTd.setName("SensorIPT");
		sensorIPTd.setFlowProperty(FlowProperty.IN);
		sensorIPTd.setLowerBound(0);
		sensorIPTd.setUpperBound(-1);
		device.getPorts().add(sensorIPTd);
		AbstractPortUse actuatorOPTd = SysADLFactory.eINSTANCE.createAbstractPortUse();
		actuatorOPTd.setName("ActuatorOPT");
		actuatorOPTd.setFlowProperty(FlowProperty.OUT);
		actuatorOPTd.setLowerBound(0);
		actuatorOPTd.setUpperBound(-1);
		device.getPorts().add(actuatorOPTd);
		AbstractPortUse serviceIPTd = SysADLFactory.eINSTANCE.createAbstractPortUse();
		serviceIPTd.setName("ServiceIPT");
		serviceIPTd.setFlowProperty(FlowProperty.IN);
		serviceIPTd.setLowerBound(0);
		serviceIPTd.setUpperBound(-1);
		device.getPorts().add(serviceIPTd);
				
		AbstractComponentDef IoTARCH = SysADLFactory.eINSTANCE.createAbstractComponentDef();
		IoTARCH.setName("IoTARCH");
		IoTARCH.getComposition().add(sensor);
		IoTARCH.getComposition().add(actuator);
		IoTARCH.getComposition().add(device);
		IoTARCH.getComposition().add(gateway);
		IoTARCH.getComposition().add(datastore);
		
		/*end Components*/
		
		/*start Connectors*/ 
		AbstractConnectorDef sensorDataCN = SysADLFactory.eINSTANCE.createAbstractConnectorDef();
		sensorDataCN.setName("SensorDataCN");
		AbstractPortUse sensorOPT_ = SysADLFactory.eINSTANCE.createAbstractPortUse();
		sensorOPT_.setName("SensorOPT");
		sensorOPT_.setFlowProperty(FlowProperty.OUT);
		sensorDataCN.getPorts().add(sensorOPT_);
		AbstractPortUse sensorIPT_ = SysADLFactory.eINSTANCE.createAbstractPortUse();
		sensorIPT_.setName("SensorIPT");
		sensorIPT_.setFlowProperty(FlowProperty.IN);
		sensorDataCN.getPorts().add(sensorIPT_);
		AbstractFlow f_sensorDataCN = SysADLFactory.eINSTANCE.createAbstractFlow();
		f_sensorDataCN.setSource(sensorOPT_);
		f_sensorDataCN.setTarget(sensorIPT_);
		sensorDataCN.getFlows().add(f_sensorDataCN);
		
		AbstractConnectorDef actuatorCommandCN = SysADLFactory.eINSTANCE.createAbstractConnectorDef();
		actuatorCommandCN.setName("ActuatorCommandCN");
		AbstractPortUse actuatorOPT_ = SysADLFactory.eINSTANCE.createAbstractPortUse();
		actuatorOPT_.setName("ActuatorOPT");
		actuatorOPT_.setFlowProperty(FlowProperty.OUT);
		actuatorCommandCN.getPorts().add(actuatorOPT_);
		AbstractPortUse actuatorIPT_ = SysADLFactory.eINSTANCE.createAbstractPortUse();
		actuatorIPT_.setName("ActuatorIPT");
		actuatorIPT_.setFlowProperty(FlowProperty.IN);
		actuatorCommandCN.getPorts().add(actuatorIPT_);
		AbstractFlow f_actuatorCommandCN = SysADLFactory.eINSTANCE.createAbstractFlow();
		f_actuatorCommandCN.setSource(actuatorOPT_);
		f_actuatorCommandCN.setTarget(actuatorIPT_);
		actuatorCommandCN.getFlows().add(f_actuatorCommandCN);
		
		AbstractConnectorDef deviceDataCN = SysADLFactory.eINSTANCE.createAbstractConnectorDef();
		deviceDataCN.setName("DeviceDataCN");
		AbstractPortUse deviceOPT_ = SysADLFactory.eINSTANCE.createAbstractPortUse();
		deviceOPT_.setName("DeviceOPT");
		deviceOPT_.setFlowProperty(FlowProperty.OUT);
		deviceDataCN.getPorts().add(deviceOPT_);
		AbstractPortUse deviceIPT_ = SysADLFactory.eINSTANCE.createAbstractPortUse();
		deviceIPT_.setName("DeviceIPT");
		deviceIPT_.setFlowProperty(FlowProperty.IN);
		deviceDataCN.getPorts().add(deviceIPT_);
		AbstractFlow f_deviceDataCN = SysADLFactory.eINSTANCE.createAbstractFlow();
		f_deviceDataCN.setSource(deviceOPT_);
		f_deviceDataCN.setTarget(deviceIPT_);
		deviceDataCN.getFlows().add(f_deviceDataCN);
		
		AbstractConnectorDef datastoreCN = SysADLFactory.eINSTANCE.createAbstractConnectorDef();
		datastoreCN.setName("DataStoreCN");
		AbstractPortUse dataOPT_ = SysADLFactory.eINSTANCE.createAbstractPortUse();
		dataOPT_.setName("DataOPT");
		dataOPT_.setFlowProperty(FlowProperty.OUT);
		datastoreCN.getPorts().add(dataOPT_);
		AbstractPortUse dataIPT_ = SysADLFactory.eINSTANCE.createAbstractPortUse();
		dataIPT_.setName("DataIPT");
		dataIPT_.setFlowProperty(FlowProperty.IN);
		datastoreCN.getPorts().add(dataIPT_);
		AbstractFlow f_datastoreCN = SysADLFactory.eINSTANCE.createAbstractFlow();
		f_datastoreCN.setSource(dataOPT_);
		f_datastoreCN.setTarget(dataIPT_);
		datastoreCN.getFlows().add(f_datastoreCN);
		
		AbstractConnectorDef serviceCN = SysADLFactory.eINSTANCE.createAbstractConnectorDef();
		serviceCN.setName("ServiceCN");
		AbstractPortUse serviceOPT_ = SysADLFactory.eINSTANCE.createAbstractPortUse();
		serviceOPT_.setName("ServiceOPT");
		serviceOPT_.setFlowProperty(FlowProperty.OUT);
		serviceCN.getPorts().add(serviceOPT_);
		AbstractPortUse serviceIPT_ = SysADLFactory.eINSTANCE.createAbstractPortUse();
		serviceIPT_.setName("ServiceIPT");
		serviceIPT_.setFlowProperty(FlowProperty.IN);
		serviceCN.getPorts().add(serviceIPT_);
		AbstractFlow f_serviceCN = SysADLFactory.eINSTANCE.createAbstractFlow();
		f_serviceCN.setSource(serviceOPT_);
		f_serviceCN.setTarget(serviceIPT_);
		serviceCN.getFlows().add(f_serviceCN);
		
		/*end Connectors*/ 
		
		/*start AbstractPins*/
		AbstractPin outSensorData = SysADLFactory.eINSTANCE.createAbstractPin();
		outSensorData.setName("outSensorData");
		
		AbstractPin inSensorData = SysADLFactory.eINSTANCE.createAbstractPin();
		inSensorData.setName("inSensorData");
		
		AbstractPin outData = SysADLFactory.eINSTANCE.createAbstractPin();
		outData.setName("outData");
		
		AbstractPin inData = SysADLFactory.eINSTANCE.createAbstractPin();
		inData.setName("inData");
		
		AbstractPin inCmd = SysADLFactory.eINSTANCE.createAbstractPin();
		inCmd.setName("inCmd");
		
		AbstractPin outCmd = SysADLFactory.eINSTANCE.createAbstractPin();
		outCmd.setName("outCmd");
		
		/*end AbstractPins*/
		
		/*start AbstractActivities*/
		AbstractActivityDef monitoring = SysADLFactory.eINSTANCE.createAbstractActivityDef();
		monitoring.setName("Monitoring");
		monitoring.getOutParameters().add(outSensorData);
				
		AbstractActivityDef analysis = SysADLFactory.eINSTANCE.createAbstractActivityDef();
		analysis.setName("Analysis");
		analysis.getInParameters().add(inSensorData);
		analysis.getOutParameters().add(outData);
		
		AbstractActivityDef planning = SysADLFactory.eINSTANCE.createAbstractActivityDef();
		planning.setName("Planning");
		planning.getInParameters().add(inData);
		planning.getOutParameters().add(outCmd);
		
		AbstractActivityDef execution = SysADLFactory.eINSTANCE.createAbstractActivityDef();
		execution.setName("Execution");
		execution.getInParameters().add(inCmd);
		
		/*end AbstractActivities*/
		
		/*start Constraints*/
		Invariant checkSensorCP = SysADLFactory.eINSTANCE.createInvariant();
		checkSensorCP.setName("checkSensorCP");
		checkSensorCP.setExpr("self.checkCPRecursive(self, 'SensorCP')");

		Invariant checkDataStoreCP = SysADLFactory.eINSTANCE.createInvariant();
		checkDataStoreCP.setName("checkDataStoreCP");
		checkDataStoreCP.setExpr("self.checkCPRecursive(self, 'DataStoreCP')");
		
		Invariant checkDeviceCP = SysADLFactory.eINSTANCE.createInvariant();
		checkDeviceCP.setName("checkDeviceCP");
		checkDeviceCP.setExpr("self.checkCPRecursive(self, 'DeviceCP')");
		
		Invariant checkSensorOPT = SysADLFactory.eINSTANCE.createInvariant();
		checkSensorOPT.setName("checkSensorOPT");
		checkSensorOPT.setExpr("self.checkPTRecursive(self, 'SensorOPT')");
		
		Invariant checkActuatorIPT = SysADLFactory.eINSTANCE.createInvariant();
		checkActuatorIPT.setName("checkActuatorIPT");
		checkActuatorIPT.setExpr("not (self.checkCPRecursive(self, 'ActuatorCP') xor self.checkPTRecursive(self, 'ActuatorIPT'))");
		
		Invariant checkDataIPT = SysADLFactory.eINSTANCE.createInvariant();
		checkDataIPT.setName("checkDataIPT");
		checkDataIPT.setExpr("self.checkPTRecursive(self, 'DataIPT')");
		
		Invariant checkDataOPT = SysADLFactory.eINSTANCE.createInvariant();
		checkDataOPT.setName("checkDataOPT");
		checkDataOPT.setExpr("self.checkPTRecursive(self, 'DataOPT')");
		
		Invariant checkServiceOPT = SysADLFactory.eINSTANCE.createInvariant();
		checkServiceOPT.setName("checkServiceOPT");
		checkServiceOPT.setExpr("not (self.checkCPRecursive(self, 'GatewayCP') xor self.checkPTRecursive(self, 'ServiceOPT'))");
		
		Invariant checkDeviceIPT = SysADLFactory.eINSTANCE.createInvariant();
		checkDeviceIPT.setName("checkDeviceIPT");
		checkDeviceIPT.setExpr("not (self.checkCPRecursive(self, 'GatewayCP') xor self.checkPTRecursive(self, 'DeviceIPT'))");
		
		Invariant checkSensorDataCN = SysADLFactory.eINSTANCE.createInvariant();
		checkSensorDataCN.setName("checkSensorDataCN");
		checkSensorDataCN.setExpr("self.checkCNRecursive(self, 'SensorDataCN')");
		
		Invariant checkDataStoreCN = SysADLFactory.eINSTANCE.createInvariant();
		checkDataStoreCN.setName("checkDataStoreCN");
		checkDataStoreCN.setExpr("self.checkCNRecursive(self, 'DataStoreCN')");
		
		Invariant checkActuatorCommandCN = SysADLFactory.eINSTANCE.createInvariant();
		checkActuatorCommandCN.setName("checkActuatorCommandCN");
		checkActuatorCommandCN.setExpr("not (self.checkCPRecursive(self, 'ActuatorCP') xor self.checkCNRecursive(self, 'ActuatorCommandCN'))");
		
		Invariant checkServiceCN = SysADLFactory.eINSTANCE.createInvariant();
		checkServiceCN.setName("checkServiceCN");
		checkServiceCN.setExpr("not (self.checkCPRecursive(self, 'GatewayCP') xor self.checkCNRecursive(self, 'ServiceCN'))");
		
		Invariant checkDeviceDataCN = SysADLFactory.eINSTANCE.createInvariant();
		checkDeviceDataCN.setName("checkDeviceDataCN");
		checkDeviceDataCN.setExpr("not (self.checkPTRecursive(self, 'DeviceOPT') xor self.checkCNRecursive(self, 'DeviceDataCN'))");
		
		Invariant ConstraintC1 = SysADLFactory.eINSTANCE.createInvariant();
		ConstraintC1.setName("ConstraintC1");
		ConstraintC1.setExpr("self.CheckActivities(self, 'Monitoring') and self.CheckActivities(self, 'Analysis') and self.CheckActivities(self, 'Planning') and self.CheckActivities(self, 'Execution')");
		
		Invariant ConstraintC3 = SysADLFactory.eINSTANCE.createInvariant();
		ConstraintC3.setName("ConstraintC3");
		ConstraintC3.setExpr("self.CheckMonitoringAct(self)");
		
		Invariant ConstraintC4 = SysADLFactory.eINSTANCE.createInvariant();
		ConstraintC4.setName("ControllerMustBeEmbeddedInDevice");
		ConstraintC4.setExpr("self.ControllerCPEmbedded(self)");
		
		Invariant ConstraintC5 = SysADLFactory.eINSTANCE.createInvariant();
		ConstraintC5.setName("SensorMustBeConnectedToDeviceOrController");
		ConstraintC5.setExpr("self.SensorConnection(self)");
		
		Invariant ConstraintC6 = SysADLFactory.eINSTANCE.createInvariant();
		ConstraintC6.setName("ActuatorMustBeConnectedToDeviceOrController");
		ConstraintC6.setExpr("self.ActuatorConnection(self)");
		
		Invariant ConstraintC7 = SysADLFactory.eINSTANCE.createInvariant();
		ConstraintC7.setName("SensorAndActuatorDontCommunicate");
		ConstraintC7.setExpr("self.Communication(self)");
		
		Invariant ConstraintC8 = SysADLFactory.eINSTANCE.createInvariant();
		ConstraintC8.setName("SensorDataCNMustNotBeMultiplex");
		ConstraintC8.setExpr("self.checkBindingsRecursive(self, 'SensorDataCN')");
		
		Invariant ConstraintC9 = SysADLFactory.eINSTANCE.createInvariant();
		ConstraintC9.setName("ActuatorCommandCNMustNotBeMultiplex");
		ConstraintC9.setExpr("self.checkBindingsRecursive(self, 'ActuatorCommandCN')");
		
		Invariant ConstraintC10 = SysADLFactory.eINSTANCE.createInvariant();
		ConstraintC10.setName("DeviceDataCNMustNotBeMultiplex");
		ConstraintC10.setExpr("self.checkBindingsRecursive(self, 'DeviceDataCN')");
		
		/*end Constraints*/
		
		/*start Protocol*/
		AbstractProtocol iotStyleProtocol = SysADLFactory.eINSTANCE.createAbstractProtocol();
		iotStyleProtocol.setName("IoTStyle");
		
		AbstractProtocolBody body = SysADLFactory.eINSTANCE.createAbstractProtocolBody();
		body.setRecControl(ProtocolControl.ALWAYS);
		
		AbstractProtocolBody bodyInternalMonitoringOut = SysADLFactory.eINSTANCE.createAbstractProtocolBody();
		bodyInternalMonitoringOut.setRecControl(ProtocolControl.SEVERAL);
		AbstractActionSend sendMonitoring = SysADLFactory.eINSTANCE.createAbstractActionSend();
		sendMonitoring.setFlowTo(outSensorData);
		bodyInternalMonitoringOut.setRecType(ProtocolAlternativeType.COMPLIMENTARY);
		
		AbstractProtocolBody bodyInternalAnalysisIn = SysADLFactory.eINSTANCE.createAbstractProtocolBody();
		bodyInternalAnalysisIn.setRecControl(ProtocolControl.SEVERAL);
		AbstractActionReceive receiveAnalysis = SysADLFactory.eINSTANCE.createAbstractActionReceive();
		receiveAnalysis.setFlowTo(inSensorData);
		bodyInternalAnalysisIn.setRecType(ProtocolAlternativeType.COMPLIMENTARY);
		
		AbstractProtocolBody bodyInternalAnalysisOut = SysADLFactory.eINSTANCE.createAbstractProtocolBody();
		bodyInternalAnalysisOut.setRecControl(ProtocolControl.SEVERAL);
		AbstractActionSend sendAnalysis = SysADLFactory.eINSTANCE.createAbstractActionSend();
		sendAnalysis.setFlowTo(outData);
		bodyInternalAnalysisOut.setRecType(ProtocolAlternativeType.COMPLIMENTARY);
		
		AbstractProtocolBody bodyInternalPlanningIn = SysADLFactory.eINSTANCE.createAbstractProtocolBody();
		bodyInternalPlanningIn.setRecControl(ProtocolControl.SEVERAL);
		AbstractActionReceive receivePlanning = SysADLFactory.eINSTANCE.createAbstractActionReceive();
		receivePlanning.setFlowTo(inData);
		bodyInternalPlanningIn.setRecType(ProtocolAlternativeType.COMPLIMENTARY);
		
		AbstractProtocolBody bodyInternalPlanningOut = SysADLFactory.eINSTANCE.createAbstractProtocolBody();
		bodyInternalPlanningOut.setRecControl(ProtocolControl.SEVERAL);
		AbstractActionSend sendPlanning = SysADLFactory.eINSTANCE.createAbstractActionSend();
		sendPlanning.setFlowTo(outCmd);
		bodyInternalPlanningOut.setRecType(ProtocolAlternativeType.COMPLIMENTARY);
		
		AbstractProtocolBody bodyInternalExecutionIn = SysADLFactory.eINSTANCE.createAbstractProtocolBody();
		bodyInternalExecutionIn.setRecControl(ProtocolControl.SEVERAL);
		AbstractActionReceive receiveExecution = SysADLFactory.eINSTANCE.createAbstractActionReceive();
		receiveExecution.setFlowTo(inCmd);
		bodyInternalExecutionIn.setBody(receiveExecution);
		
		bodyInternalPlanningOut.setRecursive(bodyInternalExecutionIn);
		bodyInternalPlanningOut.setBody(sendPlanning);
		
		bodyInternalPlanningIn.setRecursive(bodyInternalPlanningOut);
		bodyInternalPlanningIn.setBody(receivePlanning);
		
		bodyInternalAnalysisOut.setRecursive(bodyInternalPlanningIn);
		bodyInternalAnalysisOut.setBody(sendAnalysis);
		
		bodyInternalAnalysisIn.setRecursive(bodyInternalAnalysisOut);
		bodyInternalAnalysisIn.setBody(receiveAnalysis);
		
		bodyInternalMonitoringOut.setRecursive(bodyInternalAnalysisIn);
		bodyInternalMonitoringOut.setBody(sendMonitoring);
		
		body.setBody(bodyInternalMonitoringOut);		
		iotStyleProtocol.setBody(body);
		
		/*end Protocol*/
		
		IoTStyle.getDefinitions().add(sensor);
		IoTStyle.getDefinitions().add(actuator);
		IoTStyle.getDefinitions().add(device);
		IoTStyle.getDefinitions().add(datastore);
		IoTStyle.getDefinitions().add(controller);
		IoTStyle.getDefinitions().add(gateway);
		IoTStyle.getDefinitions().add(IoTARCH);
		
		IoTStyle.getDefinitions().add(sensorDataCN);
		IoTStyle.getDefinitions().add(actuatorCommandCN);
		IoTStyle.getDefinitions().add(deviceDataCN);
		IoTStyle.getDefinitions().add(datastoreCN);
		IoTStyle.getDefinitions().add(serviceCN);

		IoTStyle.getDefinitions().add(monitoring);
		IoTStyle.getDefinitions().add(analysis);
		IoTStyle.getDefinitions().add(planning);
		IoTStyle.getDefinitions().add(execution);
		
		IoTStyle.getDefinitions().add(iotStyleProtocol);
		
		IoTStyle.getInvariants().add(checkSensorCP);
		IoTStyle.getInvariants().add(checkDataStoreCP);
		IoTStyle.getInvariants().add(checkDeviceCP);
		IoTStyle.getInvariants().add(checkSensorOPT);
		IoTStyle.getInvariants().add(checkActuatorIPT);
		IoTStyle.getInvariants().add(checkDataIPT);
		IoTStyle.getInvariants().add(checkDataOPT);
		IoTStyle.getInvariants().add(checkServiceOPT);
		IoTStyle.getInvariants().add(checkDeviceIPT);
		IoTStyle.getInvariants().add(checkSensorDataCN);
		IoTStyle.getInvariants().add(checkDataStoreCN);
		IoTStyle.getInvariants().add(checkActuatorCommandCN);
		IoTStyle.getInvariants().add(checkServiceCN);
		IoTStyle.getInvariants().add(checkDeviceDataCN);
		IoTStyle.getInvariants().add(ConstraintC1);
		IoTStyle.getInvariants().add(ConstraintC3);
		IoTStyle.getInvariants().add(ConstraintC4);
		IoTStyle.getInvariants().add(ConstraintC5);
		IoTStyle.getInvariants().add(ConstraintC6);
		IoTStyle.getInvariants().add(ConstraintC7);
		IoTStyle.getInvariants().add(ConstraintC8);
		IoTStyle.getInvariants().add(ConstraintC9);
		IoTStyle.getInvariants().add(ConstraintC10);
		
		return IoTStyle;
	}

}
