import React, { useEffect, useState } from "react";
import NssButtonSubtract from "../nss/NssButtonSubtract";

function Color(props) {
  const { color: providedColor, index, updateColor, deleteColor } = props;
  const [color, setColor] = useState({});

  useEffect(() => {
    const newColor = providedColor.color
      ? providedColor.color
      : getRandomHexColor();
    setColor(newColor);
    updateColor(newColor, index);
  }, []);

  useEffect(() => {
    updateColor(color, index);
  }, [color]);

  function getRandomHexColor() {
    const randomColor = Math.floor(Math.random() * 16777215).toString(16);
    const hexColor = "#" + "0".repeat(6 - randomColor.length) + randomColor;
    return hexColor;
  }

  const onChangeColor = (event) => {
    setColor(event.target.value);
    updateColor(event.target.value, index);
  };

  const onDeleteColor = () => {
    deleteColor(index);
  };

  return (
    <div className="flex gap-1 border border-nss-300 p-1">
      <div className="border border-nss-20">
        <div className="flex space-x-2">
          <input
            id="nativeColorPicker1"
            type="color"
            value={color}
            onChange={onChangeColor}
          />
        </div>
      </div>
      <NssButtonSubtract onClick={onDeleteColor} label="Delete" />
    </div>
  );
}

export default Color;
