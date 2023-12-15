import React from "react";
import NavigationBar from "./NavigationBar";
import { HeaderData } from "../Website";

function Header() {
  return (
    <div className={`${HeaderData.bgColor} ${HeaderData.textColor} p-4 rounded-t-lg flex justify-between justify-items-center items-center w-full`}>
      <div className="flex-nowrap pr-10">
        <div className="">
          <div className={`${HeaderData.businessNameFont} ${HeaderData.businessNameSizes}`}>
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
