import React from "react";
import bannerImage from "../assets/ecoglow-innovations-high-resolution-logo.png";
import { useNavigate } from "react-router-dom";
import { WebsiteColors } from "../Website";

function Home() {
  const navigate = useNavigate();

  function products() {
    navigate("/products");
  }

  function contactUs() {
    navigate("/contact");
  }

  return (
    <div className="pb-2">
      <div className="overflow-hidden mx-auto flex items-center">
        <img
          src={bannerImage}
          alt="Image did not load."
          className="w-full h-96 object-cover"
        />
      </div>

      <div className={`${WebsiteColors.homeTextColor} flex flex-col`}>
        <div className="p-4">
          <h1 className="text-4xl text-center">
            Illuminate Your World, Sustainably.
          </h1>
        </div>

        <div className="flex justify-center items-center p-4">
          <ul className="list-none grid grid-flow-row grid-cols-2 gap-x-4 tracking-tighter font-light text-center">
            <li>
              <div className="text-xl font-bold">
                Innovative Lighting Solutions
              </div>
              <div className="">
                Specializing in cutting-edge, eco-friendly lighting solutions
                that merge innovation with sustainability.
              </div>
            </li>
            <li>
              <div className="text-xl font-bold">Solar-Powered Brilliance</div>
              <div>
                SolarGlow Lamps harness solar energy to illuminate outdoor
                spaces stylishly, reducing carbon footprint.
              </div>
            </li>
            <li>
              <div className="text-xl font-bold">Wind-Powered Ambiance</div>
              <div>
                WindBrite Lanterns utilize built-in wind turbines to generate
                energy, creating captivating outdoor lighting.
              </div>
            </li>
            <li>
              <div className="text-xl font-bold">Water-Powered Efficiency</div>
              <div>
                HydroBeam Desk Lamps feature an energy-efficient LED system
                powered by a water-based energy source for sustainable workspace
                lighting.
              </div>
            </li>
            <li>
              <div className="text-xl font-bold">Eco-Chic Interior Design</div>
              <div>
                BambooGlo Pendants, crafted from sustainable bamboo, add a touch
                of natural elegance to interior spaces, promoting an
                eco-conscious lifestyle.
              </div>
            </li>
            <li>
              <div className="text-xl font-bold">
                Functionality and Aesthetics
              </div>
              <div>
                Our lighting products seamlessly blend functionality and
                aesthetics to enhance both indoor and outdoor environments.
              </div>
            </li>
            <li>
              <div className="text-xl font-bold">Reducing Carbon Footprint</div>
              <div>
                Committed to minimizing environmental impact by offering
                energy-efficient and renewable energy-powered lighting
                solutions.
              </div>
            </li>
            <li>
              <div className="text-xl font-bold">Versatile Applications</div>
              <div>
                Lighting solutions designed for various applications, including
                gardens, patios, pathways, workspaces, and interior decor.
              </div>
            </li>
            <li>
              <div className="text-xl font-bold">
                Eco-Conscious Manufacturing
              </div>
              <div>
                Dedicated to sustainable practices in design and manufacturing,
                ensuring minimal environmental impact throughout the product
                lifecycle.
              </div>
            </li>
            <li>
              <div className="text-xl font-bold">Brighter, Greener Future</div>
              <div>
                Our mission is to contribute to a brighter, greener future by
                providing environmentally friendly lighting options for homes
                and businesses.
              </div>
            </li>
            <li>
              <div className="text-xl font-bold">
                Community and Environmental Stewardship
              </div>
              <div>
                Actively involved in initiatives that promote community
                awareness and environmental stewardship for a more sustainable
                world.
              </div>
            </li>
            <li>
              <div className="text-xl font-bold">Join the Movement</div>
              <div>
                Illuminate your world with EcoGlow and join us on the journey
                towards a brighter, greener future.
              </div>
            </li>
          </ul>
        </div>

        <div className="text-2xl text-center p-4">
          At EcoGlow Innovations, we believe in lighting up lives while
          minimizing our impact on the planet. Our mission is to provide
          innovative, sustainable lighting solutions that not only enhance the
          beauty of your surroundings but also contribute to a more
          environmentally conscious world. Illuminate your world with EcoGlow
          and join us on the journey towards a brighter, greener future.
        </div>

        <div className="flex gap-20 justify-center items-center">
          <button
            onClick={products}
            type="button"
            className={`${WebsiteColors.buttonColors} inline-flex items-center px-8 py-2 font-semibold leading-6 text-sm shadow rounded-md first-line:transition ease-in-out duration-150 cursor-pointer`}
            disabled=""
          >
            Our Products
          </button>
          <button
            onClick={contactUs}
            type="button"
            className={`${WebsiteColors.buttonColors} inline-flex items-center px-8 py-2 font-semibold leading-6 text-sm shadow rounded-md first-line:transition ease-in-out duration-150 cursor-pointer`}
            disabled=""
          >
            Contact Us
          </button>
        </div>
        <div className="text-2xl text-center p-4">
          Welcome to the future of lighting solutions with EcoGlow Innovations'
          latest breakthrough – the EcoIlluminate Pro Series. We understand that
          the world is yearning for sustainable brilliance, and our Pro Series
          is here to deliver just that.
        </div>
      </div>
    </div>
  );
}

export default Home;
