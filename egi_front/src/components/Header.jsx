import React, { useContext } from "react";
import { HeaderData } from "../Website";
import { NavLink } from "react-router-dom";
import { CartContext } from "../contexts/CartContext";
import { faShoppingCart } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

function NavigationBar() {
  const cartContext = useContext(CartContext);

  const totalItems = () => {
    if (cartContext.totalItems == 0) {
      return;
    }
    return "(" + cartContext.totalItems + ")";
  };

  return (
    <div className="text-sm font-bold text-center flex xs:flex-col xs:mt-0 xs:gap-1 sm:flex-row sm:gap-4 mt-2">
      <NavLink className={`${HeaderData.hoverTextColor}`} to="/">
        Home
      </NavLink>
      <NavLink className={`${HeaderData.hoverTextColor}`} to="/about">
        About Us
      </NavLink>
      <NavLink className={`${HeaderData.hoverTextColor}`} to="/faqs">
        FAQs
      </NavLink>
      <NavLink className={`${HeaderData.hoverTextColor}`} to="/products">
        Products
      </NavLink>
      <NavLink className={`${HeaderData.hoverTextColor}`} to="/contact">
        Contact Us
      </NavLink>
      <NavLink className={`${HeaderData.hoverTextColor}`} to="/cart">
        <FontAwesomeIcon icon={faShoppingCart} /> Cart {totalItems()}
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
