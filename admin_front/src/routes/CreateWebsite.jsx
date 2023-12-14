import React, { useState, useEffect } from "react";
import {
  DOMAINS,
  DOMAINS_ARR,
  FONT_FAMILY,
  FONT_FAMILY_ARR,
  PageType,
  PageType_Arr,
} from "../admin/Objects";
import Options from "../admin/Options";
import NssSmallInputText from "../nss/NssSmallInputText";
import NssButtonAdd from "../nss/NssButtonAdd";
import Color from "../createwebsite/Color";
import Page from "../createwebsite/Page";

/*
What pages do you want? 
*/

function CreateWebsite() {
  const [companyName, setCompanyName] = useState("");
  const [url, setUrl] = useState("");
  const [domain, setDomain] = useState(DOMAINS.com);
  const [font, setFont] = useState(FONT_FAMILY.mono);
  const [colors, setColors] = useState([]);
  const [pages, setPages] = useState([]);

  const onChangeCompanyName = (val) => {
    setCompanyName(val);
  };

  const onChangeUrl = (val) => {
    setUrl(val);
  };

  const onChangeDomain = (newDomain) => {
    setDomain(newDomain);
  };

  const onChangeFont = (newFont) => {
    setFont(newFont);
  };

  const getDemoFontFamily = () => {
    return font;
  };

  const onAddColor = () => {
    setColors([...colors, { color: "" }]);
  };

  const updateColor = (color, index) => {
    const updatedColors = [...colors];
    updatedColors[index] = { color: color };
    setColors(updatedColors);
  };

  const deleteColor = (index) => {
    const updatedColors = [...colors];
    updatedColors.splice(index, 1);
    setColors(updatedColors);
  };

  const onAddPage = () => {
    setPages([
      ...pages,
      {
        pageName: "New Page",
        pageType: PageType.UNSPECIFIED,
        pageTypesArr: PageType_Arr,
        text: "",
      },
    ]);
  };

  const updatePage = (page, index) => {
    const updatedPages = [...pages];
    updatedPages[index] = { page: page };
    setPages(updatedPages);
  };

  const deletePage = (index) => {
    const updatedPages = [...pages];
    updatedPages.splice(index, 1);
    setPages(updatedPages);
  };

  return (
    <div>
      <div className="text-center">
        Welcome to the first step towards creating your very own website.
      </div>
      <div className="flex justify-center">
        <div className="px-4 py-1">
          <div className="text-xs font-extrabold">
            What is the name of your company?
          </div>
          <NssSmallInputText
            value={companyName}
            onChange={onChangeCompanyName}
            id="company"
            placeholder="Enter company name"
            type="text"
          />
        </div>
        <div className="px-4 py-1">
          <div className="text-xs font-extrabold">What url do you want?</div>
          <div className="flex">
            <div>www.</div>
            <NssSmallInputText
              value={url}
              onChange={onChangeUrl}
              id="url"
              placeholder="Enter the url"
              type="text"
            />
            <div className="flex items-center gap-2 accent-nss-300">
              <Options
                selected={domain}
                selectOptions={DOMAINS_ARR}
                name={""}
                selectedChange={onChangeDomain}
              />
            </div>
          </div>
        </div>
      </div>
      <div className="flex justify-center">
        <div className="px-4 py-1">
          <div className="text-xs font-extrabold">
            What font family do you want?
          </div>
          <div className="flex items-center gap-2 accent-nss-300">
            <Options
              selected={font}
              selectOptions={FONT_FAMILY_ARR}
              name={""}
              selectedChange={onChangeFont}
            />
          </div>
        </div>
        <div className="flex items-center">
          <div className={`${getDemoFontFamily()} w-48`}>Your font style.</div>
        </div>
      </div>
      <NssButtonAdd onClick={onAddColor} label="Add Color" />
      <div className="flex justify-center gap-2">
        <div className="flex justify-center items-center gap-1">
          <div className="columns-4 gap-0">
            {colors.map((color, index) => (
              <div className="p-1">
                <Color
                  key={index}
                  index={index}
                  color={color}
                  updateColor={updateColor}
                  deleteColor={deleteColor}
                />
              </div>
            ))}
          </div>
        </div>
      </div>
      <NssButtonAdd onClick={onAddPage} label="Add Page" />
      {pages.map((page, index) => (
        <div className="p-1">
          <Page
            key={index}
            index={index}
            page={page}
            updatePage={updatePage}
            deletePage={deletePage}
          />
        </div>
      ))}
      <div></div>
    </div>
  );
}

export default CreateWebsite;

// <div className="text-xs font-extrabold">Select your colors:</div>
