import React, { useEffect, useState } from "react";
import DataField from "./DataField";
import NssButtonEdit from "../nss/NssButtonEdit";
import NssButtonAdd from "../nss/NssButtonAdd";

const SocialMediaProfile = (props) => {
  const {
    socialMediaProfile,
    parentIndex,
    loading,
    copyProfileToParent,
    setChangeDetected,
  } = props;
  const [newSocialMediaProfile, setNewSocialMediaProfile] = useState({
    ...socialMediaProfile,
  });
  const [showEdit, setShowEdit] = useState(false);

  const addSocialMediaProfile = () => {
    const newProfile = {
      linkedin: "",
      youtube: "",
      facebook: "",
      myspace: "",
      instagram: "",
      yelp: "",
      tiktok: "",
      x: "",
      pinterest: "",
      snapchat: "",
      whatsapp: "",
      tumblr: "",
      google: "",
    };
    setNewSocialMediaProfile(newProfile);
    copyProfileToParent(newProfile);
    setChangeDetected(true);
  };

  if (loading) {
    return (
      <div className="bg-nss-21 border rounded-lg shadow-xl py-2 px-2 mt-2">
        The data is being loaded...
      </div>
    );
  }

  if (socialMediaProfile == undefined) {
    return (
      <div className="bg-nss-21 border rounded-lg shadow-xl py-2 px-2 mt-2">
        <div className="flex content-start py-2 gap-2">
          <NssButtonAdd
            onClick={addSocialMediaProfile}
            label="Add Social Media Profile"
          ></NssButtonAdd>
        </div>
      </div>
    );
  }

  const onEditSocialMediaProfile = () => {
    setShowEdit(!showEdit);
  };

  return (
    <div className="bg-nss-21 border-2 rounded-lg shadow-xl py-2 px-2 mt-2">
      <div>Social Media Profile</div>
      <div className="grid grid-flow-row grid-cols-10 gap-2">
        <div className="text-xs font-bold">LinkedIn:</div>
        <div className="text-xs font-bold col-span-9">
          {showEdit ? (
            <input
              className="bg-nss-21 text-xs placeholder-red-600 text-green-700  shadow appearance-none border rounded w-full py-1 px-3  leading-tight focus:outline-none focus:shadow-outline"
              id="linkedin"
              type="text"
              placeholder="Enter linkedin url..."
              value={newSocialMediaProfile.linkedin}
              onChange={(e) => {
                const updatedSocialMediaProfile = { ...newSocialMediaProfile };
                updatedSocialMediaProfile.linkedin = e.target.value;
                setNewSocialMediaProfile(updatedSocialMediaProfile);
                copyProfileToParent(updatedSocialMediaProfile);
                setChangeDetected(true);
              }}
            />
          ) : (
            <DataField value={newSocialMediaProfile.linkedin} />
          )}
        </div>

        <div className="text-xs font-bold">YouTube:</div>
        <div className="text-xs font-bold col-span-9">
          {showEdit ? (
            <input
              className="bg-nss-21 text-xs placeholder-red-600 text-green-700  shadow appearance-none border rounded w-full py-1 px-3  leading-tight focus:outline-none focus:shadow-outline"
              id="youtube"
              type="text"
              placeholder="Enter youtube url..."
              value={newSocialMediaProfile.youtube}
              onChange={(e) => {
                const updatedSocialMediaProfile = { ...newSocialMediaProfile };
                updatedSocialMediaProfile.youtube = e.target.value;
                setNewSocialMediaProfile(updatedSocialMediaProfile);
                copyProfileToParent(updatedSocialMediaProfile);
                setChangeDetected(true);
              }}
            />
          ) : (
            <DataField value={newSocialMediaProfile.youtube} />
          )}
        </div>

        <div className="text-xs font-bold">Facebook:</div>
        <div className="text-xs font-bold col-span-9">
          {showEdit ? (
            <input
              className="bg-nss-21 text-xs placeholder-red-600 text-green-700  shadow appearance-none border rounded w-full py-1 px-3  leading-tight focus:outline-none focus:shadow-outline"
              id="facebook"
              type="text"
              placeholder="Enter facebook url..."
              value={newSocialMediaProfile.facebook}
              onChange={(e) => {
                const updatedSocialMediaProfile = { ...newSocialMediaProfile };
                updatedSocialMediaProfile.facebook = e.target.value;
                setNewSocialMediaProfile(updatedSocialMediaProfile);
                copyProfileToParent(updatedSocialMediaProfile);
                setChangeDetected(true);
              }}
            />
          ) : (
            <DataField value={newSocialMediaProfile.facebook} />
          )}
        </div>

        <div className="text-xs font-bold">MySpace:</div>
        <div className="text-xs font-bold col-span-9">
          {showEdit ? (
            <input
              className="bg-nss-21 text-xs placeholder-red-600 text-green-700  shadow appearance-none border rounded w-full py-1 px-3  leading-tight focus:outline-none focus:shadow-outline"
              id="myspace"
              type="text"
              placeholder="Enter myspace url..."
              value={newSocialMediaProfile.myspace}
              onChange={(e) => {
                const updatedSocialMediaProfile = { ...newSocialMediaProfile };
                updatedSocialMediaProfile.myspace = e.target.value;
                setNewSocialMediaProfile(updatedSocialMediaProfile);
                copyProfileToParent(updatedSocialMediaProfile);
                setChangeDetected(true);
              }}
            />
          ) : (
            <DataField value={socialMediaProfile.myspace} />
          )}
        </div>
        <div className="text-xs font-bold">Instagram:</div>
        <div className="text-xs font-bold col-span-9">
          {showEdit ? (
            <input
              className="bg-nss-21 text-xs placeholder-red-600 text-green-700  shadow appearance-none border rounded w-full py-1 px-3  leading-tight focus:outline-none focus:shadow-outline"
              id="instagram"
              type="text"
              placeholder="Enter instagram url..."
              value={newSocialMediaProfile.instagram}
              onChange={(e) => {
                const updatedSocialMediaProfile = { ...newSocialMediaProfile };
                updatedSocialMediaProfile.instagram = e.target.value;
                setNewSocialMediaProfile(updatedSocialMediaProfile);
                copyProfileToParent(updatedSocialMediaProfile);
                setChangeDetected(true);
              }}
            />
          ) : (
            <DataField value={socialMediaProfile.instagram} />
          )}
        </div>
        <div className="text-xs font-bold">Yelp:</div>
        <div className="text-xs font-bold col-span-9">
          {showEdit ? (
            <input
              className="bg-nss-21 text-xs placeholder-red-600 text-green-700  shadow appearance-none border rounded w-full py-1 px-3  leading-tight focus:outline-none focus:shadow-outline"
              id="yelp"
              type="text"
              placeholder="Enter yelp url..."
              value={newSocialMediaProfile.yelp}
              onChange={(e) => {
                const updatedSocialMediaProfile = { ...newSocialMediaProfile };
                updatedSocialMediaProfile.yelp = e.target.value;
                setNewSocialMediaProfile(updatedSocialMediaProfile);
                copyProfileToParent(updatedSocialMediaProfile);
                setChangeDetected(true);
              }}
            />
          ) : (
            <DataField value={socialMediaProfile.yelp} />
          )}
        </div>
        <div className="text-xs font-bold">TikTok:</div>
        <div className="text-xs font-bold col-span-9">
          {showEdit ? (
            <input
              className="bg-nss-21 text-xs placeholder-red-600 text-green-700  shadow appearance-none border rounded w-full py-1 px-3  leading-tight focus:outline-none focus:shadow-outline"
              id="tiktok"
              type="text"
              placeholder="Enter tiktok url..."
              value={newSocialMediaProfile.tiktok}
              onChange={(e) => {
                const updatedSocialMediaProfile = { ...newSocialMediaProfile };
                updatedSocialMediaProfile.tiktok = e.target.value;
                setNewSocialMediaProfile(updatedSocialMediaProfile);
                copyProfileToParent(updatedSocialMediaProfile);
                setChangeDetected(true);
              }}
            />
          ) : (
            <DataField value={socialMediaProfile.tiktok} />
          )}
        </div>
        <div className="text-xs font-bold">X (Twitter):</div>
        <div className="text-xs font-bold col-span-9">
          {showEdit ? (
            <input
              className="bg-nss-21 text-xs placeholder-red-600 text-green-700  shadow appearance-none border rounded w-full py-1 px-3  leading-tight focus:outline-none focus:shadow-outline"
              id="x"
              type="text"
              placeholder="Enter x (twitter) url..."
              value={newSocialMediaProfile.x}
              onChange={(e) => {
                const updatedSocialMediaProfile = { ...newSocialMediaProfile };
                updatedSocialMediaProfile.x = e.target.value;
                setNewSocialMediaProfile(updatedSocialMediaProfile);
                copyProfileToParent(updatedSocialMediaProfile);
                setChangeDetected(true);
              }}
            />
          ) : (
            <DataField value={socialMediaProfile.x} />
          )}
        </div>
        <div className="text-xs font-bold">Pinterest:</div>
        <div className="text-xs font-bold col-span-9">
          {showEdit ? (
            <input
              className="bg-nss-21 text-xs placeholder-red-600 text-green-700  shadow appearance-none border rounded w-full py-1 px-3  leading-tight focus:outline-none focus:shadow-outline"
              id="pinterest"
              type="text"
              placeholder="Enter pinterest url..."
              value={newSocialMediaProfile.pinterest}
              onChange={(e) => {
                const updatedSocialMediaProfile = { ...newSocialMediaProfile };
                updatedSocialMediaProfile.pinterest = e.target.value;
                setNewSocialMediaProfile(updatedSocialMediaProfile);
                copyProfileToParent(updatedSocialMediaProfile);
                setChangeDetected(true);
              }}
            />
          ) : (
            <DataField value={socialMediaProfile.pinterest} />
          )}
        </div>
        <div className="text-xs font-bold">Snapchat:</div>
        <div className="text-xs font-bold col-span-9">
          {showEdit ? (
            <input
              className="bg-nss-21 text-xs placeholder-red-600 text-green-700  shadow appearance-none border rounded w-full py-1 px-3  leading-tight focus:outline-none focus:shadow-outline"
              id="snapchat"
              type="text"
              placeholder="Enter snapchat url..."
              value={newSocialMediaProfile.snapchat}
              onChange={(e) => {
                const updatedSocialMediaProfile = { ...newSocialMediaProfile };
                updatedSocialMediaProfile.snapchat = e.target.value;
                setNewSocialMediaProfile(updatedSocialMediaProfile);
                copyProfileToParent(updatedSocialMediaProfile);
                setChangeDetected(true);
              }}
            />
          ) : (
            <DataField value={socialMediaProfile.snapchat} />
          )}
        </div>
        <div className="text-xs font-bold">WhatsApp:</div>
        <div className="text-xs font-bold col-span-9">
          {showEdit ? (
            <input
              className="bg-nss-21 text-xs placeholder-red-600 text-green-700  shadow appearance-none border rounded w-full py-1 px-3  leading-tight focus:outline-none focus:shadow-outline"
              id="whatsapp"
              type="text"
              placeholder="Enter whatsapp url..."
              value={newSocialMediaProfile.whatsapp}
              onChange={(e) => {
                const updatedSocialMediaProfile = { ...newSocialMediaProfile };
                updatedSocialMediaProfile.whatsapp = e.target.value;
                setNewSocialMediaProfile(updatedSocialMediaProfile);
                copyProfileToParent(updatedSocialMediaProfile);
                setChangeDetected(true);
              }}
            />
          ) : (
            <DataField value={socialMediaProfile.whatsapp} />
          )}
        </div>
        <div className="text-xs font-bold">Tumblr:</div>
        <div className="text-xs font-bold col-span-9">
          {showEdit ? (
            <input
              className="bg-nss-21 text-xs placeholder-red-600 text-green-700  shadow appearance-none border rounded w-full py-1 px-3  leading-tight focus:outline-none focus:shadow-outline"
              id="tumblr"
              type="text"
              placeholder="Enter tumblr url..."
              value={newSocialMediaProfile.tumblr}
              onChange={(e) => {
                const updatedSocialMediaProfile = { ...newSocialMediaProfile };
                updatedSocialMediaProfile.tumblr = e.target.value;
                setNewSocialMediaProfile(updatedSocialMediaProfile);
                copyProfileToParent(updatedSocialMediaProfile);
                setChangeDetected(true);
              }}
            />
          ) : (
            <DataField value={socialMediaProfile.tumblr} />
          )}
        </div>
        <div className="text-xs font-bold">Google:</div>
        <div className="text-xs font-bold col-span-9">
          {showEdit ? (
            <input
              className="bg-nss-21 text-xs placeholder-red-600 text-green-700  shadow appearance-none border rounded w-full py-1 px-3  leading-tight focus:outline-none focus:shadow-outline"
              id="google"
              type="text"
              placeholder="Enter google url..."
              value={newSocialMediaProfile.google}
              onChange={(e) => {
                const updatedSocialMediaProfile = { ...newSocialMediaProfile };
                updatedSocialMediaProfile.google = e.target.value;
                setNewSocialMediaProfile(updatedSocialMediaProfile);
                copyProfileToParent(updatedSocialMediaProfile);
                setChangeDetected(true);
              }}
            />
          ) : (
            <DataField value={socialMediaProfile.google} />
          )}
        </div>
      </div>
      <div className="pt-2 flex justify-start">
        <NssButtonEdit
          onClick={onEditSocialMediaProfile}
          label="Edit Social Media Profile"
          selected={showEdit}
        ></NssButtonEdit>
      </div>
    </div>
  );
};

export default SocialMediaProfile;
