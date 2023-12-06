import React, { useEffect, useState } from "react";
import { getOffices } from "../axios/api";
import { Disclosure } from "@headlessui/react";
import { ChevronUpIcon } from "@heroicons/react/20/solid";
import ApiError from "../components/ApiError";

function ContactUs() {
  const [offices, setOffices] = useState([]);
  const [hasApiError, setHasApiError] = useState(true);

  useEffect(() => {
    async function fetchOffices() {
      try {
        const data = await getOffices();
        setOffices(data);
        setHasApiError(false);
      } catch (error) {
        // console.error("Error loading offices:", error);
        setHasApiError(true);
      }
    }
    fetchOffices();
  }, []);

  // Sat, Mon, Tue, Wed, Thu, Fri, Sun
  const sortOfficeSchedule = (schedule) => {
    const newSchedule = {
      Sun: 0,
      Mon: 1,
      Tue: 2,
      Wed: 3,
      Thu: 4,
      Fri: 5,
      Sat: 6,
    };
    return schedule.sort((a, b) => newSchedule[a.day] - newSchedule[b.day]);
  };

  const DisclosureComponent = (props) => {
    const { title, contents } = props;
    return (
      <div className="w-full px-4 pt-2">
        <div className="mx-auto w-full max-w-xs rounded-2xl bg-white p-2">
          <Disclosure>
            {({ open }) => (
              <>
                <Disclosure.Button
                  className={`${
                    open
                      ? "bg-npt_colors-10 text-black"
                      : "bg-npt_colors-350 text-white"
                  } flex w-full justify-between rounded-lg px-4 py-2 text-left text-sm font-medium hover:bg-npt_colors-10 hover:text-black focus:outline-none focus-visible:ring focus-visible:ring-npt_colors-30 focus-visible:ring-opacity-75`}
                >
                  <span>{title}</span>
                  <ChevronUpIcon
                    className={`${
                      open ? "rotate-180 transform text-black" : "text-white"
                    } h-5 w-5 hover:text-black`}
                  />
                </Disclosure.Button>
                <Disclosure.Panel className="px-4 pt-2 text-sm text-gray-500 w-56">
                  {contents}
                </Disclosure.Panel>
              </>
            )}
          </Disclosure>
        </div>
      </div>
    );
  };

  const NptPhoneButton = (props) => {
    const { label, phone } = props;
    return (
      <div>
        <button
          type="button"
          className="fill-white hover:fill-black inline-flex items-center px-8 py-2 font-semibold leading-6 text-sm shadow rounded-md text-white bg-npt_colors-350 hover:bg-npt_colors-10 hover:text-black first-line:transition ease-in-out duration-150 cursor-pointer"
          disabled=""
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="1em"
            viewBox="0 0 512 512"
          >
            <path d="M164.9 24.6c-7.7-18.6-28-28.5-47.4-23.2l-88 24C12.1 30.2 0 46 0 64C0 311.4 200.6 512 448 512c18 0 33.8-12.1 38.6-29.5l24-88c5.3-19.4-4.6-39.7-23.2-47.4l-96-40c-16.3-6.8-35.2-2.1-46.3 11.6L304.7 368C234.3 334.7 177.3 277.7 144 207.3L193.3 167c13.7-11.2 18.4-30 11.6-46.3l-40-96z" />
          </svg>
          <a target="_blank" rel="noopener noreferrer" href={"tel: " + phone}>
            {label}
          </a>
        </button>
      </div>
    );
  };

  const NptEmailButton = (props) => {
    const { label, email } = props;
    return (
      <div>
        <button
          type="button"
          className="fill-white hover:fill-black inline-flex items-center px-8 py-2 font-semibold leading-6 text-sm shadow rounded-md text-white bg-npt_colors-350 hover:bg-npt_colors-10 hover:text-black first-line:transition ease-in-out duration-150 cursor-pointer"
          disabled=""
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="1em"
            viewBox="0 0 512 512"
          >
            <path d="M48 64C21.5 64 0 85.5 0 112c0 15.1 7.1 29.3 19.2 38.4L236.8 313.6c11.4 8.5 27 8.5 38.4 0L492.8 150.4c12.1-9.1 19.2-23.3 19.2-38.4c0-26.5-21.5-48-48-48H48zM0 176V384c0 35.3 28.7 64 64 64H448c35.3 0 64-28.7 64-64V176L294.4 339.2c-22.8 17.1-54 17.1-76.8 0L0 176z" />
          </svg>
          <a
            target="_blank"
            rel="noopener noreferrer"
            href={
              "mailto: " +
              email +
              "?subject=Hello, I would like to inquire about..."
            }
          >
            {label}
          </a>
        </button>
      </div>
    );
  };

  const NptGoogleMapsButton = (props) => {
    const { label, mapUrl } = props;
    return (
      <div>
        <button
          type="button"
          className="fill-white hover:fill-black inline-flex items-center px-8 py-2 font-semibold leading-6 text-sm shadow rounded-md text-white bg-npt_colors-350 hover:bg-npt_colors-10 hover:text-black first-line:transition ease-in-out duration-150 cursor-pointer"
          disabled=""
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="1em"
            viewBox="0 0 576 512"
          >
            <path d="M384 476.1L192 421.2V35.9L384 90.8V476.1zm32-1.2V88.4L543.1 37.5c15.8-6.3 32.9 5.3 32.9 22.3V394.6c0 9.8-6 18.6-15.1 22.3L416 474.8zM15.1 95.1L160 37.2V423.6L32.9 474.5C17.1 480.8 0 469.2 0 452.2V117.4c0-9.8 6-18.6 15.1-22.3z" />
          </svg>
          <a target="_blank" rel="noopener noreferrer" href={mapUrl}>
            {label}
          </a>
        </button>
      </div>
    );
  };

  const Schedule = (props) => {
    const { day, beginTime, endTime, index } = props;
    const today = new Date();
    const options = { weekday: "short" };
    const dayOfWeek = new Intl.DateTimeFormat("en-US", options).format(today);
    const now = new Date();
    const startTimeDate = new Date();
    const endTimeDate = new Date();
    const beginTimeComponent = beginTime.split(":");
    const endTimeComponent = endTime.split(":");
    const beginHour = parseInt(beginTimeComponent[0], 10);
    const beginMinute = parseInt(beginTimeComponent[1], 10);
    const endHour = parseInt(endTimeComponent[0], 10);
    const endMinute = parseInt(endTimeComponent[1], 10);
    const beginMeredian = setMeredian(beginHour);
    const endMeredian = setMeredian(endHour);

    function setMeredian(hour) {
      if (hour > 12) {
        return "PM";
      } else {
        return "AM";
      }
    }

    function maskHour(hour) {
      if (hour > 12) {
        return hour % 12;
      }
      return hour;
    }

    function maskMinute(minute) {
      if (minute == 0) {
        return "00";
      } else {
        return minute;
      }
    }

    startTimeDate.setHours(beginHour, beginMinute);

    endTimeDate.setHours(endHour, endMinute);

    if (dayOfWeek === day) {
      return (
        <div className="grid grid-cols-9" key={index}>
          <div className="col-span-1">{day}</div>
          <div className="col-span-2 text-right">
            {maskHour(beginHour) +
              ":" +
              maskMinute(beginMinute) +
              " " +
              beginMeredian}
          </div>
          <div className="col-span-1 text-center">{"-"}</div>
          <div className="col-span-2 text-left">
            {maskHour(endHour) +
              ":" +
              maskMinute(endMinute) +
              " " +
              endMeredian}
          </div>
          {now >= startTimeDate && now <= endTimeDate ? (
            <div className="col-span-3 text-npt_colors-300">We are open!</div>
          ) : (
            <div className="col-span-3 text-red-800">We are closed!</div>
          )}
        </div>
      );
    }

    return (
      <div className="grid grid-cols-9" key={index}>
        <div className="col-span-1">{day}</div>
        <div className="col-span-2 text-right">
          {maskHour(beginHour) +
            ":" +
            maskMinute(beginMinute) +
            " " +
            beginMeredian}
        </div>
        <div className="col-span-1 text-center">{"-"}</div>
        <div className="col-span-2 text-left">
          {maskHour(endHour) + ":" + maskMinute(endMinute) + " " + endMeredian}
        </div>
      </div>
    );
  };

  const WalkIns = (props) => {
    const { acceptingWalkIns } = props;
    return (
      <div className="flex text-lg">
        {!acceptingWalkIns ? (
          <>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="1.5em"
              viewBox="0 0 512 512"
            >
              <path d="M288 32c0-17.7-14.3-32-32-32s-32 14.3-32 32V240c0 8.8-7.2 16-16 16s-16-7.2-16-16V64c0-17.7-14.3-32-32-32s-32 14.3-32 32V336c0 1.5 0 3.1 .1 4.6L67.6 283c-16-15.2-41.3-14.6-56.6 1.4s-14.6 41.3 1.4 56.6L124.8 448c43.1 41.1 100.4 64 160 64H304c97.2 0 176-78.8 176-176V128c0-17.7-14.3-32-32-32s-32 14.3-32 32V240c0 8.8-7.2 16-16 16s-16-7.2-16-16V64c0-17.7-14.3-32-32-32s-32 14.3-32 32V240c0 8.8-7.2 16-16 16s-16-7.2-16-16V32z" />
            </svg>
            <div>We do not accept walk-ins!</div>
          </>
        ) : (
          <>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="1.5em"
              viewBox="0 0 640 512"
            >
              <path d="M208 96a48 48 0 1 0 0-96 48 48 0 1 0 0 96zM123.7 200.5c1-.4 1.9-.8 2.9-1.2l-16.9 63.5c-5.6 21.1-.1 43.6 14.7 59.7l70.7 77.1 22 88.1c4.3 17.1 21.7 27.6 38.8 23.3s27.6-21.7 23.3-38.8l-23-92.1c-1.9-7.8-5.8-14.9-11.2-20.8l-49.5-54 19.3-65.5 9.6 23c4.4 10.6 12.5 19.3 22.8 24.5l26.7 13.3c15.8 7.9 35 1.5 42.9-14.3s1.5-35-14.3-42.9L281 232.7l-15.3-36.8C248.5 154.8 208.3 128 163.7 128c-22.8 0-45.3 4.8-66.1 14l-8 3.5c-32.9 14.6-58.1 42.4-69.4 76.5l-2.6 7.8c-5.6 16.8 3.5 34.9 20.2 40.5s34.9-3.5 40.5-20.2l2.6-7.8c5.7-17.1 18.3-30.9 34.7-38.2l8-3.5zm-30 135.1L68.7 398 9.4 457.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L116.3 441c4.6-4.6 8.2-10.1 10.6-16.1l14.5-36.2-40.7-44.4c-2.5-2.7-4.8-5.6-7-8.6zM550.6 153.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L530.7 224H384c-17.7 0-32 14.3-32 32s14.3 32 32 32H530.7l-25.4 25.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0l80-80c12.5-12.5 12.5-32.8 0-45.3l-80-80z" />
            </svg>
            <div>We accept walk-ins!</div>
          </>
        )}
      </div>
    );
  };

  return (
    <div className="">
      {offices.map((office, index) => (
        <div key={index} className="flex justify-around flex-row m-2">
          <div>
            <div className="bg-npt_colors-350 text-white rounded-t-xl px-2 pt-2 text-center break-words">
              {office.introduction}
            </div>
            <div className="relative bg-white px-2 pb-2 shadow-xl w-fit ring-1 ring-gray-900/5 rounded-b-xl">
              <div className="py-2 flex justify-center">
                <div>
                  <WalkIns acceptingWalkIns={false} />
                </div>
              </div>
              <div className="flex flex-row gap-4 justify-center">
                <NptPhoneButton label="Call Us" phone={office.phone} />
                <NptEmailButton label="Email Us" email={office.email} />
                <NptGoogleMapsButton
                  label="Google Map Us"
                  mapUrl={office.mapUrl}
                />
              </div>
              <div>
                <div className="grid grid-cols-2">
                  <div className="px-2 py-2 w-56">
                    <div>
                      <div className="text-xs font-semibold">Email</div>
                      <div className="text-sm text-npt_colors-1">
                        {office.email}
                      </div>
                    </div>
                    <div>
                      <div className="text-xs font-semibold">Fax</div>
                      <div className="text-sm text-npt_colors-1">
                        {office.fax}
                      </div>
                    </div>
                    <div>
                      <div className="text-xs font-semibold">Phone</div>
                      <div className="text-sm text-npt_colors-1">
                        {office.phone}
                      </div>
                    </div>
                    <div>
                      <div className="text-xs font-semibold">Address</div>
                      <div className="text-sm text-npt_colors-1">
                        {office.street +
                          "., " +
                          office.unit +
                          ", " +
                          office.city +
                          ", " +
                          office.state +
                          ", " +
                          office.zip}
                      </div>
                    </div>
                  </div>
                  <div className="py-2 px-2 flex items-center justify-center">
                    <div>
                      {sortOfficeSchedule(office.schedule).map(
                        (schedule, index) => (
                          <div>
                            <Schedule
                              index={index}
                              day={schedule.day}
                              beginTime={schedule.beginTime}
                              endTime={schedule.endTime}
                            />
                          </div>
                        )
                      )}
                    </div>
                  </div>
                </div>
                <div className="flex justify-center">
                  {hasApiError ? <ApiError /> : <></>}
                </div>
              </div>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}

export default ContactUs;

/*

Old V1 (makes accessing data needlessly difficult)

 <div className="text-center">V1</div>
      {offices.map((office, index) => (
        <div key={index} className="flex justify-around flex-row">
          <div>
            <div className="bg-npt_colors-350 text-white rounded-t-xl px-2 pt-2 text-center">
              {office.introduction}
            </div>
            <div className="relative bg-white px-2 pt-2 pb-2 shadow-xl w-fit ring-1 ring-gray-900/5 rounded-b-xl">
              <div className="py-2 flex justify-center">
                <div>
                  <WalkIns acceptingWalkIns={office.acceptingWalkIns} />
                </div>
              </div>
              <div className="flex flex-row gap-4 justify-center">
                <NptPhoneButton label="Call Us" phone={office.phone} />
                <NptEmailButton label="Email Us" email={office.email} />
                <NptGoogleMapsButton
                  label="Google Map Us"
                  mapUrl={office.mapUrl}
                />
              </div>
              <div>
                <div className="grid grid-cols-2">
                  <div>
                    <DisclosureComponent
                      title={"Email"}
                      contents={office.email}
                    />
                    <DisclosureComponent title={"Fax"} contents={office.fax} />
                    <DisclosureComponent
                      title={"Phone"}
                      contents={office.phone}
                    />
                    <DisclosureComponent
                      title={"Address"}
                      contents={
                        office.street +
                        "., " +
                        office.unit +
                        ", " +
                        office.city +
                        ", " +
                        office.state +
                        ", " +
                        office.zip
                      }
                    />
                  </div>
                  <div className="py-2 px-2 flex items-center justify-center">
                    <div>
                      {sortOfficeSchedule(office.schedule).map(
                        (schedule, index) => (
                          <div>
                            <Schedule
                              index={index}
                              day={schedule.day}
                              beginTime={schedule.beginTime}
                              endTime={schedule.endTime}
                            />
                          </div>
                        )
                      )}
                    </div>
                  </div>
                </div>
                <div className="flex justify-center">
                  {hasApiError ? <ApiError /> : <></>}
                </div>
              </div>
            </div>
          </div>
        </div>
      ))}

*/
