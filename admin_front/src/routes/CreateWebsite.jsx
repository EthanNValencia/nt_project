import React, { useState, useEffect } from "react";
import {
  DOMAINS,
  DOMAINS_ARR,
  FONT_FAMILY,
  FONT_FAMILY_ARR,
} from "../admin/Objects";
import Options from "../admin/Options";
import NssSmallInputText from "../nss/NssSmallInputText";

/*
Company Name? 
What URL do you want?
What pages do you want? 
*/

function CreateWebsite() {
  const [companyName, setCompanyName] = useState("");
  const [url, setUrl] = useState("");
  const [domain, setDomain] = useState(DOMAINS.com);
  const [font, setFont] = useState(FONT_FAMILY.mono);
  const [primaryColor, setPrimaryColor] = useState("#6590D5");
  const [secondaryColor, setSecondaryColor] = useState("#48BF40");

  const onChangeCompanyName = (val) => {
    setCompanyName(val);
  };

  const onChangeUrl = (val) => {
    setUrl(val);
  };

  const onChangeRole = (newRole) => {
    setDomain(newRole);
  };

  const onChangeFont = (newFont) => {
    setFont(newFont);
  };

  const getDemoFontFamily = () => {
    return font;
  };

  const onChangePrimaryColor = (event) => {
    const newColor = event.target.value;
    setPrimaryColor(newColor);
  };

  const onChangeSecondaryColor = (event) => {
    const newColor = event.target.value;
    setSecondaryColor(newColor);
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
                selectedChange={onChangeRole}
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
      <div className="flex justify-center gap-2">
        <div className="flex justify-center items-center gap-2">
          <div className="text-xs font-extrabold">
            Select your primary color:
          </div>
          <div className="border border-nss-20">
            <div className="flex space-x-2">
              <input
                id="nativeColorPicker1"
                type="color"
                value={primaryColor}
                onChange={onChangePrimaryColor}
              />
            </div>
          </div>
        </div>
        <div className="flex justify-center items-center gap-2">
          <div className="text-xs font-extrabold">
            Select your secondary color:
          </div>
          <div className="border border-nss-20">
            <div className="flex space-x-2">
              <input
                id="nativeColorPicker1"
                type="color"
                value={secondaryColor}
                onChange={onChangeSecondaryColor}
              />
            </div>
          </div>
        </div>
      </div>
      <div></div>
    </div>
  );
}

export default CreateWebsite;
