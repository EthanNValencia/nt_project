import React from "react";

function SimpleButton(props) {
  const { onClick, label } = props;

  return (
    <button
      onClick={onClick}
      type="button"
      className="inline-flex items-center px-8 py-2 font-semibold leading-6 text-sm shadow rounded-md text-btn_light-text bg-btn_light-bg hover:bg-btn_light-hover hover:text-btn_light-hover_text first-line:transition ease-in-out duration-150 cursor-pointer"
      disabled=""
    >
      {label}
    </button>
  );
}

export default SimpleButton;
