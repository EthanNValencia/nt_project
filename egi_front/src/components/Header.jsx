import React from "react";
import { HeaderData } from "../Website";
import { NavLink } from "react-router-dom";
import { WebsiteColors } from "../Website";

function NavigationBar() {
  return (
    <div className="text-sm font-bold text-center flex xs:flex-col xs:mt-0 xs:gap-1 sm:flex-row sm:gap-4 mt-2">
      <NavLink className={`${WebsiteColors.hoverLight}`} to="/">
        Home
      </NavLink>
      <NavLink className={`${WebsiteColors.hoverLight}`} to="/about">
        About Us
      </NavLink>
      <NavLink className={`${WebsiteColors.hoverLight} `} to="/faqs">
        FAQs
      </NavLink>
      <NavLink className={`${WebsiteColors.hoverLight}`} to="/products">
        Products
      </NavLink>
      <NavLink className={`${WebsiteColors.hoverLight}`} to="/contact">
        Contact Us
      </NavLink>
    </div>
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
