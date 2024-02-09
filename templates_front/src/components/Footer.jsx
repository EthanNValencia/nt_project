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

const Google = (props) => {
  const { google } = props;
  return (
    <>
      {google && (
        <div>
          <a target="_blank" rel="noopener noreferrer" href={google}>
            <div className="">
              <FontAwesomeIcon
                icon={faGoogle}
                className={`${FooterData.iconColors} ${FooterData.iconSizes} transition ease-in-out delay-150 hover:scale-125 duration-300`}
              />
            </div>
          </a>
        </div>
      )}
    </>
  );
};

const Tumblr = (props) => {
  const { tumblr } = props;
  return (
    <>
      {tumblr && (
        <div>
          <a target="_blank" rel="noopener noreferrer" href={tumblr}>
            <div className="">
              <FontAwesomeIcon
                icon={faTumblr}
                className={`${FooterData.iconColors} ${FooterData.iconSizes} transition ease-in-out delay-150 hover:scale-125 duration-300`}
              />
            </div>
          </a>
        </div>
      )}
    </>
  );
};

const Whatsapp = (props) => {
  const { whatsapp } = props;
  return (
    <>
      {whatsapp && (
        <div>
          <a target="_blank" rel="noopener noreferrer" href={whatsapp}>
            <div className="">
              <FontAwesomeIcon
                icon={faWhatsapp}
                className={`${FooterData.iconColors} ${FooterData.iconSizes} transition ease-in-out delay-150 hover:scale-125 duration-300`}
              />
            </div>
          </a>
        </div>
      )}
    </>
  );
};

const Snapchat = (props) => {
  const { snapchat } = props;
  return (
    <>
      {snapchat && (
        <div>
          <a target="_blank" rel="noopener noreferrer" href={snapchat}>
            <div className="">
              <FontAwesomeIcon
                icon={faSnapchat}
                className={`${FooterData.iconColors} ${FooterData.iconSizes} transition ease-in-out delay-150 hover:scale-125 duration-300`}
              />
            </div>
          </a>
        </div>
      )}
    </>
  );
};

const Pinterest = (props) => {
  const { pinterest } = props;
  return (
    <>
      {pinterest && (
        <div>
          <a target="_blank" rel="noopener noreferrer" href={pinterest}>
            <div className="">
              <FontAwesomeIcon
                icon={faPinterest}
                className={`${FooterData.iconColors} ${FooterData.iconSizes} transition ease-in-out delay-150 hover:scale-125 duration-300`}
              />
            </div>
          </a>
        </div>
      )}
    </>
  );
};

const XTwitter = (props) => {
  const { x } = props;
  return (
    <>
      {x && (
        <div>
          <a target="_blank" rel="noopener noreferrer" href={x}>
            <div className="">
              <FontAwesomeIcon
                icon={faXTwitter}
                className={`${FooterData.iconColors} ${FooterData.iconSizes} transition ease-in-out delay-150 hover:scale-125 duration-300`}
              />
            </div>
          </a>
        </div>
      )}
    </>
  );
};

const Tiktok = (props) => {
  const { tiktok } = props;
  return (
    <>
      {tiktok && (
        <div>
          <a target="_blank" rel="noopener noreferrer" href={tiktok}>
            <div className="">
              <FontAwesomeIcon
                icon={faTiktok}
                className={`${FooterData.iconColors} ${FooterData.iconSizes} transition ease-in-out delay-150 hover:scale-125 duration-300`}
              />
            </div>
          </a>
        </div>
      )}
    </>
  );
};

const Linkedin = (props) => {
  const { linkedin } = props;
  return (
    <>
      {linkedin && (
        <div>
          <a target="_blank" rel="noopener noreferrer" href={linkedin}>
            <div className="">
              <FontAwesomeIcon
                icon={faLinkedin}
                className={`${FooterData.iconColors} ${FooterData.iconSizes} transition ease-in-out delay-150 hover:scale-125 duration-300`}
              />
            </div>
          </a>
        </div>
      )}
    </>
  );
};

const Youtube = (props) => {
  const { youtube } = props;
  return (
    <>
      {youtube && (
        <div>
          <a target="_blank" rel="noopener noreferrer" href={youtube}>
            <div className="">
              <FontAwesomeIcon
                icon={faYoutube}
                className={`${FooterData.iconColors} ${FooterData.iconSizes} transition ease-in-out delay-150 hover:scale-125 duration-300`}
              />
            </div>
          </a>
        </div>
      )}
    </>
  );
};

const Instagram = (props) => {
  const { instagram } = props;
  return (
    <>
      {instagram && (
        <div>
          <a target="_blank" rel="noopener noreferrer" href={instagram}>
            <div className="">
              <FontAwesomeIcon
                icon={faInstagram}
                className={`${FooterData.iconColors} ${FooterData.iconSizes} transition ease-in-out delay-150 hover:scale-125 duration-300`}
              />
            </div>
          </a>
        </div>
      )}
    </>
  );
};

const Yelp = (props) => {
  const { yelp } = props;
  return (
    <>
      {yelp && (
        <div>
          <a target="_blank" rel="noopener noreferrer" href={yelp}>
            <div className="">
              <FontAwesomeIcon
                icon={faYelp}
                className={`${FooterData.iconColors} ${FooterData.iconSizes} transition ease-in-out delay-150 hover:scale-125 duration-300`}
              />
            </div>
          </a>
        </div>
      )}
    </>
  );
};

const Facebook = (props) => {
  const { facebook } = props;
  return (
    <>
      {facebook && (
        <div>
          <a target="_blank" rel="noopener noreferrer" href={facebook}>
            <div className="">
              <FontAwesomeIcon
                icon={faFacebook}
                className={`${FooterData.iconColors} ${FooterData.iconSizes} transition ease-in-out delay-150 hover:scale-125 duration-300`}
              />
            </div>
          </a>
        </div>
      )}
    </>
  );
};

function Footer() {
  const height = 36;
  const width = 36;

  return (
    <div className={`${FooterData.bgColor} ${FooterData.textColor} p-4`}>
      <div className="flex justify-between justify-items-center items-center ">
        <div className="flex gap-4">
          <Pinterest pinterest={SocialMediaProfile.pinterest} />
          <Snapchat snapchat={SocialMediaProfile.snapchat} />
          <XTwitter x={SocialMediaProfile.x} />
          <Linkedin linkedin={SocialMediaProfile.linkedin} />
          <Google google={SocialMediaProfile.google} />
          <Tiktok tiktok={SocialMediaProfile.tiktok} />
          <Facebook facebook={SocialMediaProfile.facebook} />
          <Youtube youtube={SocialMediaProfile.youtube} />
          <Yelp yelp={SocialMediaProfile.yelp} />
          <Tumblr tumblr={SocialMediaProfile.tumblr} />
          <Instagram instagram={SocialMediaProfile.instagram} />
          <Whatsapp whatsapp={SocialMediaProfile.whatsapp} />
        </div>
        <div className="text-xs">Developed by Nephew Software Solutions</div>
      </div>
    </div>
  );
}

export default Footer;
