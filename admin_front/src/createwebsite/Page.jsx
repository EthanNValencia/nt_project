import React, { useState } from "react";
import TextArea from "../admin/TextArea";
import Options from "../admin/Options";
import NssSmallInputText from "../nss/NssSmallInputText";

function Page(props) {
  const { page: providedPage, index, updatePage, deletePage } = props;
  const [page, setPage] = useState(providedPage);

  const changeText = (newText) => {
    updatePage({ ...page, text: newText }, index);
    setPage({ ...page, text: newText });
  };

  const onChangePageType = (newPageType) => {
    updatePage({ ...page, pageType: newPageType }, index);
    setPage({ ...page, pageType: newPageType });
  };

  const doNothing = (value) => {};

  const onChangePageName = (newPageName) => {
    updatePage({ ...page, pageName: newPageName }, index);
    setPage({ ...page, pageName: newPageName });
  };

  return (
    <div>
      {page.pageName}
      <div>
        <div className="flex justify-between">
          <div className="flex items-center">
            <div className="text-xs font-bold pr-1">Page Type:</div>
            <Options
              selected={page.pageType}
              selectOptions={page.pageTypesArr}
              name={""}
              selectedChange={onChangePageType}
            />
          </div>
          <div className="flex items-center">
            <div className="text-xs font-bold pb-1 pr-1">Page Name:</div>
            <div className="w-36">
              <NssSmallInputText
                value={page.pageName}
                onChange={onChangePageName}
                id="page"
                placeholder="Enter page name"
                type="text"
              />
            </div>
          </div>
        </div>
        <div className="text-xs font-bold pb-1">Page Text:</div>
        <TextArea
          text={page.text}
          onTextChange={changeText}
          changeDetected={doNothing}
        />
      </div>
    </div>
  );
}

export default Page;
