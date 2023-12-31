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
import { Tab, Tabs, TabList, TabPanel } from "react-tabs";
import "./react-tabs.css";

/*
What pages do you want? 
*/

const TabComponent = (props) => {
  const { updatePage, deletePage, addPage, pages: providedPages } = props;
  const [selectedTab, setSelectedTab] = useState(0);
  const [pages, setPages] = useState(providedPages);

  useEffect(() => {
    setPages(providedPages);
  }, [providedPages]);

  const handleTabSelect = (index) => {
    setSelectedTab(index);
  };

  return (
    <div className="p-2">
      <Tabs selectedIndex={selectedTab} onSelect={handleTabSelect}>
        <TabList>
          {pages.map((page, index) => (
            <Tab key={index}>{page.pageName}</Tab>
          ))}
          <Tab onClick={addPage}>Add Page +</Tab>
        </TabList>
        {pages.map((page, index) => (
          <div className="bg-nss-20 -mt-2.5 rounded-sm">
            <TabPanel key={index}>
              <Page
                key={index}
                index={index}
                page={page}
                updatePage={updatePage}
                deletePage={deletePage}
              />
            </TabPanel>
          </div>
        ))}
      </Tabs>
    </div>
  );
};

function CreateWebsite() {
  const [companyName, setCompanyName] = useState("");
  const [url, setUrl] = useState("");
  const [domain, setDomain] = useState(DOMAINS.com);
  const [primaryFont, setPrimaryFont] = useState(FONT_FAMILY.mono);
  const [secondaryFont, setSecondaryFont] = useState(FONT_FAMILY.mono);
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

  const onChangePrimaryFont = (newFont) => {
    setPrimaryFont(newFont);
  };

  const getDemoPrimaryFontFamily = () => {
    return primaryFont;
  };

  const onChangeSecondaryFont = (newFont) => {
    setSecondaryFont(newFont);
  };

  const getDemoSecondaryFontFamily = () => {
    return secondaryFont;
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
    if (pages.length < 8) {
      setPages([
        ...pages,
        {
          pageName: "New Page",
          pageType: PageType.UNSPECIFIED,
          pageTypesArr: PageType_Arr,
          text: "",
        },
      ]);
    }
  };

  const updatePage = (page, index) => {
    const updatedPages = [...pages];
    updatedPages[index] = { ...page };
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
          <div className="text-xs font-extrabold">Select primary font:</div>
          <div className="flex items-center gap-2 accent-nss-300">
            <Options
              selected={primaryFont}
              selectOptions={FONT_FAMILY_ARR}
              name={""}
              selectedChange={onChangePrimaryFont}
            />
          </div>
        </div>
        <div className="flex items-center">
          <div className={`${getDemoPrimaryFontFamily()} w-48`}>
            Primary style.
          </div>
        </div>
        <div className="px-4 py-1">
          <div className="text-xs font-extrabold">Select secondary font:</div>
          <div className="flex items-center gap-2 accent-nss-300">
            <Options
              selected={secondaryFont}
              selectOptions={FONT_FAMILY_ARR}
              name={""}
              selectedChange={onChangeSecondaryFont}
            />
          </div>
        </div>
        <div className="flex items-center">
          <div className={`${getDemoSecondaryFontFamily()} w-48`}>
            Secondary style.
          </div>
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
      <div>
        <TabComponent
          updatePage={updatePage}
          deletePage={deletePage}
          pages={pages}
          addPage={onAddPage}
        />
      </div>
      <div className="flex">
        <div className="flex">
          <div className="App">
            <div className="text-md text-center font-bold">NPT Example</div>
            <iframe
              className=" border border-nss-300"
              src="http://localhost:4000"
              width="800"
              height="600"
            ></iframe>
          </div>
        </div>
      </div>
    </div>
  );
}

export default CreateWebsite;
