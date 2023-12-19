import React, { useState } from "react";
import { HeaderData } from "../Website";
import { NavLink } from "react-router-dom";

function NavigationBar() {
  const [selected, setSelected] = useState(false);

  const onClick = () => {
    setSelected(!selected);
    console.log(selected);
  };

  return (
    <header>
      <nav className="text-xs font-bold text-center flex xs:flex-col xs:mt-0 xs:gap-1 sm:flex-row sm:gap-4 mt-2">
        <NavLink className="hover:text-npt_colors-300" to="/">
          Home
        </NavLink>
        <NavLink className="hover:text-npt_colors-300" to="/about">
          About Us
        </NavLink>
        <NavLink className="hover:text-npt_colors-300" to="/faqs">
          FAQ
        </NavLink>
        <NavLink className="hover:text-npt_colors-300" to="/products">
          Products
        </NavLink>
        <NavLink className="hover:text-npt_colors-300" to="/contact">
          Contact Us
        </NavLink>
      </nav>
    </header>
  );
}

function Header() {
  return (
    <div
      className={`${HeaderData.bgColor} ${HeaderData.textColor} p-4 flex justify-between justify-items-center items-center w-full`}
    >
      <div className="flex-nowrap pr-10">
        <div className="">
          <div
            className={`${HeaderData.businessNameFont} ${HeaderData.businessNameSizes}`}
          >
            {HeaderData.businessName}
          </div>
          <div className={`${HeaderData.sloganFont} ${HeaderData.sloganSizes}`}>
            {HeaderData.slogan}
          </div>
        </div>
      </div>
      <div className="flex-shrink">
        <NavigationBar />
      </div>
    </div>
  );
}

export default Header;
