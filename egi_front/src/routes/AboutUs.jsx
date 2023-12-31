import React, { useEffect, useState } from "react";
import { getEmployees } from "../axios/api";
import ApiError from "../components/ApiError";
import { Executives } from "../Website";

function ExecutiveCard(props) {
  const { executive } = props;

  return (
    <div>
      <div className="bg-egi-20 w-full p-4 border-t-2 border-b-2 border-egi-60">
        <div className="flex">
          <div>
            <div className="w-56">
              <img
                className="w-56 border border-egi-40 rounded-lg"
                src={executive.image}
              ></img>
            </div>
          </div>
          <div className="pl-4">
            <div>
              <div className="font-bold py-1">{executive.position}</div>
              <div className="font-bold py-1">
                {executive.title} {executive.firstName} {executive.lastName}{" "}
                {executive.credentials}
              </div>
            </div>
            <div className="py-1">{executive.bio}</div>
          </div>
        </div>
      </div>
      <div className="p-1 bg-egi-30"></div>
    </div>
  );
}

function AboutUs() {
  const [loading, setLoading] = useState(true);
  const [employees, setEmployees] = useState([]);
  const [hasApiError, setHasApiError] = useState(false);

  useEffect(() => {
    setLoading(true);
    async function getNptEmployees() {
      getEmployees(setLoading, setEmployees);
    }
    // getNptEmployees();
  }, []);

  useEffect(() => {
    async function fetchEmployees() {
      try {
        const data = await getEmployees();
        setEmployees(data);
        setHasApiError(false);
        console.log(JSON.stringify(data));
      } catch (error) {
        console.error("Error loading employees:", error);
        setHasApiError(true);
      }
    }
    // fetchEmployees();
  }, []);

  return (
    <div className="text-egi-60">
      <p className="p-4 tracking-tighter">
        Welcome to EcoGlow Innovations, where sustainability meets brilliance.
        At EcoGlow, we are dedicated to illuminating a brighter and greener
        future through cutting-edge, eco-friendly lighting solutions. Our
        passion lies in the intersection of innovation, functionality, and
        environmental consciousness. From harnessing the power of the sun with
        our SolarGlow Lamps to embracing the gentle breeze with our WindBrite
        Lanterns, we redefine the way you experience and interact with light. At
        the heart of our mission is a commitment to providing lighting solutions
        that not only enhance the beauty of your surroundings but also
        contribute to a more sustainable and eco-conscious world. Join us on
        this illuminating journey as we pave the way for a brighter, greener
        tomorrow. Illuminate your world sustainably with EcoGlow Innovations.
      </p>
      <div>
        {Executives.map((executive) => (
          <ExecutiveCard executive={executive} />
        ))}
      </div>
      <div className="flex justify-center">
        {hasApiError ? <ApiError /> : <></>}
      </div>
    </div>
  );
}

export default AboutUs;
