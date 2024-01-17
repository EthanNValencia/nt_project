import React, { useEffect, useState } from "react";
import { getOffices } from "../axios/api";
import ApiError from "../components/ApiError";
import { ContactInformation } from "../Website";
import PhoneButton from "../components/PhoneButton";
import EmailButton from "../components/EmailButton";
import GoogleMapsButton from "../components/GoogleMapsButton";

const Office = (props) => {
  const { office, hasApiError } = props;

  const sortOfficeSchedule = (schedule) => {
    const newSchedule = {
      Sunday: 0,
      Monday: 1,
      Tuesday: 2,
      Wednesday: 3,
      Thursday: 4,
      Friday: 5,
      Saturday: 6,
    };
    return schedule.sort((a, b) => newSchedule[a.day] - newSchedule[b.day]);
  };

  return (
    <div className="flex justify-around flex-row">
      <div>
        <div className="bg-egi-60 text-white text-center break-words p-2 text-2xl">
          {office.introduction}
        </div>
        <div className="relative px-2 pb-2 ring-1 ring-gray-900/5">
          <div className="py-2 flex justify-center">
            <div>
              <WalkIns acceptingWalkIns={office.acceptingWalkIns} />
            </div>
          </div>
          <div className="flex flex-row gap-4 justify-center">
            {office.phone ? (
              <PhoneButton label="Call Us" phone={office.phone} />
            ) : (
              <></>
            )}
            {office.email ? (
              <EmailButton label="Email Us" email={office.email} />
            ) : (
              <></>
            )}
            {office.mapUrl ? (
              <GoogleMapsButton label="Google Map Us" mapUrl={office.mapUrl} />
            ) : (
              <></>
            )}
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
                  <div className="text-sm text-npt_colors-1">{office.fax}</div>
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
                      ", " +
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
        <div className="p-2 text-white bg-egi-60 text-2xl text-center">
          {office.outroduction}
        </div>
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

const CustomerSupport = (props) => {};

const Schedule = (props) => {
  const { day, beginTime, endTime, index } = props;
  const today = new Date();
  const options = { weekday: "long" };
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
          {maskHour(endHour) + ":" + maskMinute(endMinute) + " " + endMeredian}
        </div>
        {now >= startTimeDate && now <= endTimeDate ? (
          <div className="col-span-3 text-green-800">We are open!</div>
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

function ContactUs() {
  const [contactInformation, setContactInformation] = useState(
    ContactInformation.contactInformation
  );
  const [hasApiError, setHasApiError] = useState(false);

  useEffect(() => {
    async function fetchOffices() {
      try {
        const data = await getOffices();
        setContactInformation(data);
        setHasApiError(false);
      } catch (error) {
        setHasApiError(true);
      }
    }
    // fetchOffices();
  }, []);
  return (
    <div className="">
      {contactInformation.map((contact, index) =>
        contact.type === ContactInformation.contactType.office ? (
          <Office key={index} office={contact} hasApiError={hasApiError} />
        ) : contact.type === ContactInformation.contactType.customerSupport ? (
          <CustomerSupport
            key={index}
            office={contact}
            hasApiError={hasApiError}
          />
        ) : (
          <React.Fragment key={index} />
        )
      )}
    </div>
  );
}

export default ContactUs;
