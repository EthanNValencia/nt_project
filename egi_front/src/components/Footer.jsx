import React from "react";
import { FooterData } from "../Website";
import {
  faFacebook,
  faInstagram,
  faYelp,
  faYoutube,
  faTiktok,
  faLinkedin,
  faXTwitter,
  faPinterest,
  faSnapchat,
  faWhatsapp,
  faTumblr,
  faGoogle,
} from "@fortawesome/free-brands-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { SocialMediaProfile } from "../Website";

function Footer() {
  const youtube = "https://www.youtube.com/";
  const facebook = "https://www.facebook.com/";
  const instagram = "https://www.instagram.com/";
  const yelp = "https://www.yelp.com/";
  const height = 36;
  const width = 36;

  // fill-black hover:fill-black

  return (
    <div className={`${FooterData.bgColor} ${FooterData.textColor} p-4`}>
      <div className="flex justify-between justify-items-center items-center ">
        <div className="flex gap-4">
          <a
            target="_blank"
            rel="noopener noreferrer"
            href={SocialMediaProfile.facebook}
          >
            <div className="">
              <FontAwesomeIcon
                icon={faFacebook}
                className="w-9 h-9 hover:text-egi-20 text-egi-40 transition ease-in-out delay-150 hover:scale-125 duration-300"
              />
            </div>
          </a>
          <a
            target="_blank"
            rel="noopener noreferrer"
            href={SocialMediaProfile.instagram}
          >
            <div className="">
              <FontAwesomeIcon
                icon={faInstagram}
                className="w-9 h-9 hover:text-egi-20 text-egi-40 transition ease-in-out delay-150 hover:scale-125 duration-300"
              />
            </div>
          </a>
          <a
            target="_blank"
            rel="noopener noreferrer"
            href={SocialMediaProfile.yelp}
          >
            <div className="">
              <FontAwesomeIcon
                icon={faYelp}
                className="w-9 h-9 hover:text-egi-20 text-egi-40 transition ease-in-out delay-150 hover:scale-125 duration-300"
              />
            </div>
          </a>
          <a
            target="_blank"
            rel="noopener noreferrer"
            href={SocialMediaProfile.youtube}
          >
            <div className="">
              <FontAwesomeIcon
                icon={faYoutube}
                className="w-9 h-9 hover:text-egi-20 text-egi-40 transition ease-in-out delay-150 hover:scale-125 duration-300"
              />
            </div>
          </a>
        </div>
        <div className="text-xs">Developed by Nephew Software Solutions</div>
      </div>
    </div>
  );
}

export default Footer;
