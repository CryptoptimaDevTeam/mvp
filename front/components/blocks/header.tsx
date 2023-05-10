import React, { useState } from "react";
import Image from "next/image";
import { HiArrowNarrowRight } from "react-icons/hi";
import { AiOutlineClear } from "react-icons/ai";
import { FaMoon, FaSun } from "react-icons/fa";

interface HeaderPropsType {}

type DropdownOpenVar = "none" | "user" | "alert";

export const Header: React.FC<HeaderPropsType> = () => {
  const [dropdownOpen, setDropdownOpen] = useState<DropdownOpenVar>("none");

  const navigationClassName: string =
    "block text-sm transition-all duration-300 ease-in-out hover:text-mainDownColor py-5 px-2.5";
  const dropdownClassName: string =
    "border-[1px] border-borderColor rounded-lg overflow-hidden bg-white shadow-[0_5px_20px_0_rgba(11,7,110,.04)] px-5 float-right mr-7 mt-[-5px]";

  return (
    <div className="header-componentainer sticky top-0 z-50">
      <header className="header-wrapper flex h-[70px] px-6 justify-between items-center shadow-[0_5px_20px_0_rgba(11,7,110,.04)] bg-white">
        <div className="logo-area cursor-pointer">
          <div className="logo-image w-[150px]">
            <Image
              className="logo"
              src="/image/logo/logo_full_l.svg"
              alt="logo"
              width="1613"
              height="363"
            />
          </div>
        </div>
        <nav className="navigation-area">
          <ul className="navigation-list flex gap-5">
            <li className="Register-Rebate cursor-pointer">
              <a className={`${navigationClassName}`}>Register Rebate</a>
            </li>
            <li className="Exchange-Fee-Calculator cursor-pointer">
              <a className={`${navigationClassName}`}>Rebation Calculator</a>
            </li>
            <li className="How-It-Works cursor-pointer">
              <a className={`${navigationClassName}`}>How It Works</a>
            </li>
            <li className="Crpyto-Screener cursor-pointer">
              <a className={`${navigationClassName}`}>Crypto Screener</a>
            </li>
            <li className="Event cursor-pointer">
              <a className={`${navigationClassName}`}>Event</a>
            </li>
          </ul>
        </nav>
        <div className="icon-area w-[150px] h-full flex justify-end">
          <div
            className="flex items-center"
            onMouseLeave={() => {
              setDropdownOpen("none");
            }}
          >
            <div className="icon-image w-fit flex gap-2.5 cursor-pointer p-2 border-[1px] border-borderColor rounded-full bg-[#f9f9fa]">
              <Image
                className="bg-[#878692] rounded-full"
                src="/image/icon/icon_alert.svg"
                alt="alert"
                width="30"
                height="30"
                onMouseEnter={() => {
                  setDropdownOpen("alert");
                }}
              />
              <Image
                className="bg-[#878692] rounded-full"
                src="/image/icon/icon_user.svg"
                alt="alert"
                width="30"
                height="30"
                onMouseEnter={() => {
                  setDropdownOpen("user");
                }}
              />
            </div>
          </div>
        </div>
      </header>

      <div
        className={`alert-dropdown ${
          dropdownOpen === "alert" ? "" : "hidden"
        } ${dropdownClassName} w-[450px] py-5 px-0`}
        onMouseEnter={() => {
          setDropdownOpen("alert");
        }}
        onMouseLeave={() => {
          setDropdownOpen("none");
        }}
      >
        <div className="alert-summary-wrapper px-5">
          <div className="flex justify-between items-center p-2.5 rounded-lg bg-[#f5f5f5]">
            <div className="unread-alert-count h-[30px] flex items-center text-sm">
              <span className="text-mainColor font-bold pr-2 text-lg pb-[2px]">
                {"2"}
              </span>
              New Notifications
            </div>
            <div className="view-more-btn h-[30px] flex items-center text-xs cursor-pointer text-[#7d7d7d] ">
              View More
              <span className="pl-1">
                <HiArrowNarrowRight />
              </span>
            </div>
          </div>
        </div>
        <div className="uread-alert-wrapper overflow-y-scroll max-h-[350px]">
          <ul className="uread-alert-list pt-2.5 flex flex-col gap-2.5">
            <li className="unread-alert flex flex-col gap-1 cursor-pointer px-5 py-2.5 hover:bg-[#f8f8f8]">
              <div className="alert-title font-medium text-sm">
                {"Withdrawal request on Binance is completed!"}
              </div>
              <div className="alert-body text-[#7d7d7d] text-sm line-clamp-1">
                {
                  "The withdrawal has been completed according to your request for 500usdt withdrawal from the Binance Exchange."
                }
              </div>
              <div className="alert-date text-[#7d7d7d] text-xs">
                2023-05-09 23:55
              </div>
            </li>
          </ul>
        </div>
        <div className="all-status-change-btn flex justify-center items-center text-xs text-[#979797] pt-5">
          <div className="w-fit flex justify-center items-center cursor-pointer">
            <span className="pr-1">
              <AiOutlineClear />
            </span>
            Mark all as read
          </div>
        </div>
      </div>

      <div
        className={`user-dropdown ${
          dropdownOpen === "user" ? "" : "hidden"
        } ${dropdownClassName} w-[300px]`}
        onMouseEnter={() => {
          setDropdownOpen("user");
        }}
        onMouseLeave={() => {
          setDropdownOpen("none");
        }}
      >
        <div className="status-section py-5 border-borderColor border-b-[1px]">
          {/* 비로그인 상태 */}
          <div className="flex flex-col items-center justify-center">
            <div className="welcome-message text-mainColor font-bold pb-5">
              Welcome to Cryptoptima!
            </div>
            <div className="signin-btn w-full">
              <button className="bg-mainColor text-white w-full py-2 rounded-lg transition-all hover:scale-[1.03]">
                Login
              </button>
            </div>
          </div>
          {/* 로그인 상태 */}
          <div className="user-info flex hidden">
            <div className="user-name text-lg font-bold">{"Kopa_Trader"}</div>
          </div>
        </div>
        {/* 로그인 상태 */}
        <div className="mypage-related-wrapper py-2.5 border-borderColor border-b-[1px] hidden">
          <ul className="mypage-related-list cursor-pointer">
            <li className="mypage-btn py-2.5 text-[15px] hover:text-mainDownColor">
              My page
            </li>
            <li className="rebate-record py-2.5 text-[15px] hover:text-mainDownColor">
              Rebate Record
            </li>
            <li className="withdrawl-request py-2.5 text-[15px] hover:text-mainDownColor">
              Withdrawl Request
            </li>
          </ul>
        </div>
        {/* 공통 */}
        <div className="environment-setting py-2.5">
          <ul className="environment-setting-list">
            <li className="language-btn-wrapper flex justify-between py-2.5 cursor-pointer">
              <div className="language-btn-label text-[15px]">Language</div>
              <ul className="language-btn-list flex">
                <li className="language-english border-r-[1px] border-borderColor pr-2.5 text-[15px] hover:text-mainDownColor">
                  EN
                </li>
                <li className="language-korean border-r-[1px] border-borderColor px-2.5 text-[15px] hover:text-mainDownColor">
                  KR
                </li>
                <li className="language-chinese pl-2.5 text-[15px] hover:text-mainDownColor">
                  CN
                </li>
              </ul>
            </li>
            <li className="theme-btn-wrapper cursor-pointer py-2.5 flex justify-between items-center">
              <div className="theme-btn-label text-[15px] hover:text-mainDownColor">
                Dark Theme
                {/* {Light Theme} */}
              </div>
              <div className="theme-btn">
                <FaMoon size={22} color={"#f4ce4e"} />
                {/* {<FaSun size={22} color={"#f4ce4e"} />} */}
              </div>
            </li>
          </ul>
        </div>
        {/* 로그인 상태 */}
        <div className="signout-btn py-5 text-[15px] border-borderColor border-t-[1px] cursor-pointer hover:text-mainDownColor hidden">
          Sign Out
        </div>
      </div>
    </div>
  );
};
