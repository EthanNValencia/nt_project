import React, { useContext, useState, useEffect } from "react";
import { HeaderData } from "../Website";
import { NavLink } from "react-router-dom";
import { CartContext } from "../contexts/CartContext";
import { faShoppingCart, faBars } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Transition } from "@headlessui/react";

function NavigationBar() {
  const cartContext = useContext(CartContext);
  const [screenSize, setScreenSize] = useState({
    width: window.innerWidth,
    height: window.innerHeight,
  });
  const [showMenu, setShowMenu] = useState(false);

  const large = 1024;

  const updateScreenSize = () => {
    setScreenSize({
      width: window.innerWidth,
      height: window.innerHeight,
    });
  };

  useEffect(() => {
    window.addEventListener("resize", updateScreenSize);
    return () => {
      window.removeEventListener("resize", updateScreenSize);
    };
  }, []);

  const totalItems = () => {
    if (cartContext.totalItems == 0) {
      return;
    }
    return "(" + cartContext.totalItems + ")";
  };

  return (
    <>
      {screenSize.width >= large ? (
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
      ) : (
        <div className="relative">
          <div
            className={`${HeaderData.hoverTextColor} transition ease-in-out delay-150 hover:scale-125 duration-300`}
          >
            <button
              onClick={() => setShowMenu((showMenu) => !showMenu)}
              selected={showMenu}
            >
              <FontAwesomeIcon icon={faBars} />
            </button>
          </div>
          <Transition
            show={showMenu}
            enter="transition-opacity duration-500"
            enterFrom="opacity-0"
            enterTo="opacity-100"
            leave="transition-opacity duration-500"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
          >
            <div>
              <div className="absolute top-6 -left-28 h-32 w-32">
                <div className="border border-egi-30 bg-egi-60 rounded-md shadow-lg p-1 text-white flex flex-col">
                  <NavLink className={`${HeaderData.hoverTextColor}`} to="/">
                    Home
                  </NavLink>
                  <NavLink
                    className={`${HeaderData.hoverTextColor}`}
                    to="/about"
                  >
                    About Us
                  </NavLink>
                  <NavLink
                    className={`${HeaderData.hoverTextColor}`}
                    to="/faqs"
                  >
                    FAQs
                  </NavLink>
                  <NavLink
                    className={`${HeaderData.hoverTextColor}`}
                    to="/products"
                  >
                    Products
                  </NavLink>
                  <NavLink
                    className={`${HeaderData.hoverTextColor}`}
                    to="/contact"
                  >
                    Contact Us
                  </NavLink>
                  <NavLink
                    className={`${HeaderData.hoverTextColor}`}
                    to="/cart"
                  >
                    <FontAwesomeIcon icon={faShoppingCart} /> Cart{" "}
                    {totalItems()}
                  </NavLink>
                </div>
              </div>
            </div>
          </Transition>
        </div>
      )}
    </>
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
