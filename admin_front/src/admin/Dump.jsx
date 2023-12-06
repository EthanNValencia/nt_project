import React, { useState, useEffect } from "react";

function Dump(props) {
  const { data } = props;
  const [searchString, setSearchString] = useState("");
  const [string, setString] = useState(JSON.stringify(data));
  const [highLightedText, setHightLightedText] = useState("");

  useEffect(() => {
    searchText();
  }, [searchString]);

  function highlightWord(text, word) {
    const regex = new RegExp(word, "gi");
    const resultText = text.replace(
      regex,
      (match) => `<span class="bg-yellow-400">${match}</span>`
    );
    return resultText;
  }

  const searchText = () => {
    const highlightedText = highlightWord(string, searchString);
    setHightLightedText(highlightedText);
  };

  return (
    <div className="shadow-xl min-w-0 border rounded-md p-1 mt-1">
      <div className="flex items-center justify-left">
        <div className="text-xs font-bold pr-2">Search:</div>
        <div className="pr-2">
          <input
            className="bg-nss-21 w-48 text-xs placeholder-red-600 shadow appearance-none border rounded py-1 px-3 text-green-700 leading-tight focus:outline-none focus:shadow-outline"
            id="searchString"
            type="text"
            placeholder="Search"
            value={searchString}
            onChange={(e) => {
              setSearchString(e.target.value);
            }}
          />
        </div>
      </div>
      <div className="shadow-xl min-w-0 border rounded-md p-1 mt-1 break-words">
        <div>
          {highLightedText == "" ? (
            <div>{string}</div>
          ) : (
            <span dangerouslySetInnerHTML={{ __html: highLightedText }} />
          )}
        </div>
      </div>
    </div>
  );
}

export default Dump;
