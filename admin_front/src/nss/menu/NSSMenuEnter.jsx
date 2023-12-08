import React from "react";
import { faRightToBracket } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

function NSSMenuEnter(props) {
  const { onClick, label, disabled, selected, animateBounce } = props;

  const pickDivColor = () => {
    if (disabled) {
      return "bg-nss-10 text-nss-20";
    }
    if (selected && selected != undefined) {
      return "text-nss-10 bg-nss-305 hover:bg-nss-300 hover:text-white";
    }
    if (!disabled) {
      return "text-nss-20 bg-nss-300 hover:bg-nss-305 hover:text-nss-10";
    }
  };

  return (
    <div>
      <div className="flex flex-col">
        <button
          type="button"
          className={`${pickDivColor()} inline-flex justify-center font-semibold leading-6 text-xs shadow rounded transition ease-in-out duration-500 cursor-pointer`}
          onClick={onClick}
          disabled={disabled}
        >
          <div className="inline-flex items-center gap-2">
            {label}
            <FontAwesomeIcon icon={faRightToBracket} />
          </div>
        </button>
      </div>
    </div>
  );
}

export default NSSMenuEnter;
