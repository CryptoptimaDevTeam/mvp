import React from "react";
import Image from "next/image";

interface FoooterPropsType {}

export const Footer: React.FC<FoooterPropsType> = () => {
  return (
    <footer className="bg-[#f1f1f4] py-[56px]">
      <div className="footer-container max-w-[1248px] px-6 mx-auto text-sm">
        <div className="footer-section1">
          <ul className="footer-content-list text-[#121214] flex gap-8 pb-5 border-b-[1px] border-borderColor">
            <li>
              <a className="cursor-pointer">Register Rebate</a>
            </li>
            <li>
              <a className="cursor-pointer">Rebation Calculator</a>
            </li>
            <li>
              <a className="cursor-pointer">How It Works</a>
            </li>
            <li>
              <a className="cursor-pointer">Crypto Screener</a>
            </li>
            <li>
              <a className="cursor-pointer">Event</a>
            </li>
            <li>
              <a className="cursor-pointer">Company</a>
            </li>
            <li>
              <a className="cursor-pointer">FAQ</a>
            </li>
          </ul>
        </div>
        <div className="footer-section2 flex justify-between">
          <div className="footer-info flex flex-col gap-5 pt-5 text-[#72717d]">
            <div className="footer-policy flex">
              <a className="terms-of-service cursor-pointer">
                Terms of Service
              </a>
              <span className="px-2">|</span>
              <a className="privacy-policy cursor-pointer">Privacy Policy</a>
            </div>
            <div className="footer-contact">cryptoptima.official@gmail.com</div>
            <div className="footer-copyright">
              © 2022 Cryptoptima All rights reserved
            </div>
          </div>
          <div className="footer-sns">
            <ul className="sns-list flex gap-5 pt-5">
              <li>
                <a className="cursor-pointer">
                  <Image
                    className=""
                    src="/image/logo/logo_twitter.svg"
                    alt="twitter"
                    width={30}
                    height={30}
                  />
                </a>
              </li>
              <li className="pr-[2px]">
                <a className="cursor-pointer">
                  <Image
                    className=""
                    src="/image/logo/logo_facebook.svg"
                    alt="facebook"
                    width={30}
                    height={30}
                  />
                </a>
              </li>
              <li>
                <a className="cursor-pointer">
                  <Image
                    className=""
                    src="/image/logo/logo_youtube.svg"
                    alt="youtube"
                    width={30}
                    height={30}
                  />
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </footer>
  );
};
