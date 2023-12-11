import React, { useState, useEffect, useContext } from "react";
import { AuthContext } from "../contexts/context";
import {
  adminGetServices,
  adminPutServices,
  adminDeleteService,
  serviceAddTextApi,
  updateServiceApi,
} from "../axios/api";
import ApiError from "../components/ApiError";
import DataField from "./DataField";
import ToolTip from "./ToolTip";
import NssButtonAdd from "../nss/NssButtonAdd";
import NssButtonSubtract from "../nss/NssButtonSubtract";
import NssButtonEdit from "../nss/NssButtonEdit";
import NssButtonReload from "../nss/NssButtonReload";
import NssButtonSave from "../nss/NssButtonSave";
import StatusMessage, { pickDivColor } from "./StatusMessage";
import NssButtonChevron from "../nss/NssButtonChevron";
import Dump from "./Dump";
import Texts from "./Texts";

function Services() {
  const [services, setServices] = useState([]);
  const [loading, setLoading] = useState(false);
  const [hasApiError, setHasApiError] = useState(false);
  const [changeDetected, setChangeDetected] = useState(false);
  const [editMode, setEditMode] = useState(false);
  const authContext = useContext(AuthContext);

  // console.log(JSON.stringify(services));

  const newService = () => {
    const newService = {
      name: "",
      employees: [],
    };
    const updatedServices = [...services, { ...newService }];
    setServices(updatedServices);
    setChangeDetected(true);
  };

  useEffect(() => {
    fetchServices();
  }, []);

  async function fetchServices() {
    try {
      setLoading(true);
      const data = await adminGetServices(authContext.token);
      setServices(data);
      // console.log(JSON.stringify(data));
      setHasApiError(false);
      setLoading(false);
      setChangeDetected(false);
    } catch (error) {
      setLoading(false);
      setHasApiError(true);
      setChangeDetected(false);
      // console.log("There was an error fetching the website data.");
      // console.error("Error loading employees:", error);
    }
  }

  const removeServiceFromArray = (service) => {
    const updatedServicesArray = services.filter((s) => s.id !== service.id);
    setServices(updatedServicesArray);
  };

  async function deleteService(service) {
    try {
      setLoading(true);
      const data = await adminDeleteService(service.id, authContext.token);
      // setServices(data);
      // console.log(JSON.stringify(data));
      removeServiceFromArray(service);
      setHasApiError(false);
      setLoading(false);
      setChangeDetected(false);
    } catch (error) {
      setLoading(false);
      setHasApiError(true);
      setChangeDetected(false);
      // console.log("There was an error fetching the website data.");
      // console.error("Error loading employees:", error);
    }
  }

  async function putServices() {
    try {
      setLoading(true);
      const data = await adminPutServices(services, authContext.token);
      setServices(data);
      // console.log(JSON.stringify(data));
      setHasApiError(false);
      setLoading(false);
      setChangeDetected(false);
    } catch (error) {
      setLoading(false);
      setHasApiError(true);
      setChangeDetected(false);
      // console.log("There was an error fetching the website data.");
      // console.error("Error loading employees:", error);
    }
  }

  const saveServices = () => {
    putServices();
  };

  const reloadServices = () => {
    fetchServices();
  };

  const updateServices = (service, index) => {
    const newServices = [...services];
    newServices[index] = { ...service };
    setServices(newServices);
  };

  return (
    <div
      className={`${pickDivColor(
        loading,
        editMode,
        changeDetected
      )} border rounded-lg shadow-xl pb-2 px-2 my-2`}
    >
      <div className="flex justify-between">
        <div className="flex gap-2 py-2">
          <NssButtonAdd onClick={newService} label="New Service"></NssButtonAdd>
          <NssButtonReload
            onClick={reloadServices}
            label="Reload Services"
          ></NssButtonReload>
          <NssButtonSave
            animateBounce={changeDetected}
            onClick={saveServices}
            label="Save Services"
          ></NssButtonSave>
        </div>
        <div>
          <StatusMessage
            loading={loading}
            editMode={editMode}
            changeDetected={changeDetected}
          />
        </div>
      </div>
      <div>
        <div className="grid grid-cols-1 gap-1">
          {services.map((service, index) => (
            <Service
              key={service.id}
              service={service}
              index={index}
              changeDetected={changeDetected}
              setChangeDetected={setChangeDetected}
              deleteService={deleteService}
              undefined={!service.id || !service.name}
              updateServices={updateServices}
            />
          ))}
        </div>
        <div>{hasApiError ? <ApiError /> : <></>}</div>
      </div>
    </div>
  );
}

function Service(props) {
  const {
    service,
    index,
    changeDetected,
    setChangeDetected,
    deleteService,
    undefined,
    updateServices,
  } = props;
  const [localService, setLocalService] = useState({ ...service });
  const [showDump, setShowDump] = useState(false);
  const [editMode, setEditMode] = useState(undefined);
  const [showTexts, setShowTexts] = useState(false);
  const [loading, setLoading] = useState(false);
  const [hasApiError, setHasApiError] = useState(false);
  const authContext = useContext(AuthContext);

  const onEditService = () => {
    if (editMode) {
      updateServices(localService, index);
    }
    setEditMode(!editMode);
    setShowDump(false);
    setShowTexts(false);
  };

  const onOpenDump = () => {
    setEditMode(false);
    setShowDump(!showDump);
    setShowTexts(false);
  };

  const onShowTexts = () => {
    setEditMode(false);
    setShowDump(false);
    setShowTexts(!showTexts);
  };

  const addNewServiceText = async (position) => {
    if (changeDetected) {
      saveService();
    }
    try {
      setLoading(true);
      const updatedService = await serviceAddTextApi(
        position,
        localService,
        authContext.token
      );
      setLocalService(updatedService);
      setHasApiError(false);
      setLoading(false);
    } catch (error) {
      setLoading(false);
      setHasApiError(true);
      console.log("There was an error adding a new service text.");
    }
  };

  const saveService = async () => {
    if (changeDetected && !editMode) {
      try {
        setLoading(true);
        const updatedService = await updateServiceApi(
          {
            ...localService,
          },
          authContext.token
        );
        setLocalService(updatedService);
        setHasApiError(false);
        setLoading(false);
        setChangeDetected(false);
      } catch (error) {
        setLoading(false);
        setHasApiError(true);
        console.log("There was an error saving the service.");
      }
    }
  };

  const deleteThisService = () => {
    deleteService(localService);
  };

  const updateServiceTexts = (texts) => {
    const updatedService = { ...localService };
    updatedService.serviceTexts = texts;
    setLocalService(updatedService);
  };

  const generateText = () => {
    if (localService.employees.length == 0) {
      return "You have no employees assigned to this service. Please assign an employee to this service.";
    } else if (localService.employees.length == 1) {
      return "You only have one employee assigned to this service. Does this service need more employees?";
    } else {
      return "This service has multiple employees providing it.";
    }
  };

  const getParentObject = () => {
    const service = { id: localService.id };
    return service;
  };

  return (
    <div>
      <div
        key={localService.id}
        className={`${pickDivColor(
          editMode
        )} border rounded-lg shadow-xl py-2 px-2`}
      >
        <div>
          <div className="flex justify-between">
            <div>
              <div className="text-xs pr-2">Service Name:</div>
              {editMode ? (
                <input
                  className="bg-nss-21 text-xs placeholder-red-600 shadow appearance-none border rounded w-full py-1 px-3 text-green-700 leading-tight focus:outline-none focus:shadow-outline"
                  id="servicename"
                  type="text"
                  placeholder="Enter service name..."
                  value={localService.name}
                  onChange={(e) => {
                    const updatedService = { ...localService };
                    updatedService.name = e.target.value;
                    setLocalService(updatedService);
                  }}
                />
              ) : (
                <DataField value={localService.name} />
              )}
            </div>
            <div>
              <div className="text-xs font-bold pr-2 inline-flex">
                Employees
                <ToolTip text={generateText()} />
              </div>
              <div className="text-xs pr-2 text-center font-bold">
                {localService.employees.length}
              </div>
            </div>
          </div>
        </div>
        <div className="flex gap-2 justify-between pt-2">
          <div className="flex gap-2">
            <NssButtonEdit onClick={onEditService} label="Edit"></NssButtonEdit>
            <NssButtonChevron
              onClick={onOpenDump}
              label="Dump"
              selected={showDump}
            ></NssButtonChevron>
            <NssButtonChevron
              onClick={onShowTexts}
              label="Texts"
              selected={showTexts}
            ></NssButtonChevron>
          </div>
          <NssButtonSubtract
            onClick={deleteThisService}
            label="Delete"
            disabled={service.id == null || localService.employees.length >= 1}
          ></NssButtonSubtract>
        </div>
        {showDump ? <Dump data={localService} /> : <></>}
        {showTexts ? (
          <Texts
            parentTexts={localService.serviceTexts}
            setChangeDetected={setChangeDetected}
            updateTexts={updateServiceTexts}
            name={"Service Text"}
            parentObjectWithId={getParentObject()}
            addText={addNewServiceText}
          />
        ) : (
          <></>
        )}
      </div>
    </div>
  );
}

export default Services;

/*

<div className="flex justify-end">
                <div>
                  <ToolTipAdmin />
                </div>
              </div>

*/
