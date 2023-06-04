import React, { useState } from "react";
import Image from "next/image";
import { MainButton } from "../atoms/button";
import { Modal } from "../atoms/modal";
import { HiArrowNarrowRight } from "react-icons/hi";
import { AiOutlineClear } from "react-icons/ai";
import { FaMoon, FaSun } from "react-icons/fa";
import Link from "next/link";
import { useRouter } from "next/router";
import { headerNavList } from "../../data/headerStatic";
import { useAppSelector } from "../../ducks/store";

interface HeaderPropsType {}

type DropdownOpenVar = "none" | "user" | "alert";
type loginSignupVar = "none" | "login" | "signup";

export const Header: React.FC<HeaderPropsType> = () => {
  const router = useRouter();
  const { pathname } = router;
  const { isLogin, username } = useAppSelector((state) => state.loginIdentity);

  const [dropdownOpen, setDropdownOpen] = useState<DropdownOpenVar>("none");
  const [loginSignup, setLoginSignup] = useState<loginSignupVar>("none");
  const [userModal, setUserModal] = useState(false);

  const navigationClassName: string = `block text-sm transition-all duration-500 ease-in-out hover:text-mainDownColor py-5 px-2.5`;
  const navigationAfterClassName: string = `after:transition-all after:duration-500 after:absolute 
  after:bottom-0 after:left-0 after:right-0 after:m-auto after:w-0 after:rounded-xl
  after:text-transparent after:bg-mainColor after:h-[2px] after:content-['.'] hover:after:w-full`;
  const dropdownClassName: string =
    "border-[1px] border-borderColor rounded-lg overflow-hidden bg-white shadow-[0_5px_20px_0_rgba(11,7,110,.04)] px-5 float-right mr-7 mt-[-5px]";
  return (
    <div className="header-componentainer sticky top-0 z-50 min-w-[1248px]">
      <div className="relative">
        <header className="header-wrapper flex h-[70px] px-6 justify-between items-center shadow-[0_5px_20px_0_rgba(11,7,110,.04)] bg-white">
          <div className="logo-area cursor-pointer">
            <Link href="/">
              <div className="logo-image w-[150px]">
                <Image
                  className="logo"
                  src="/image/logo/logo_full_l.svg"
                  alt="logo"
                  width="1613"
                  height="363"
                />
              </div>
            </Link>
          </div>
          <nav className="navigation-area">
            <ul className="navigation-list flex gap-5">
              {headerNavList.map((el) => (
                <li
                  className={`${el.className} cursor-pointer`}
                  key={el.className}
                >
                  <Link
                    href={el.link}
                    className={`${navigationClassName} ${
                      pathname.includes(el.link) && "text-mainColor"
                    } relative ${navigationAfterClassName}`}
                  >
                    {el.navName}
                  </Link>
                </li>
              ))}
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
          className={`alert-dropdown absolute right-0 ${
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
          <div className="uread-alert-wrapper">
            <ul className="uread-alert-list pt-2.5 flex flex-col gap-2.5 max-h-[350px] overflow-y-auto">
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
          className={`user-dropdown absolute right-0 ${
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
            {isLogin ? (
              <div className="user-info flex">
                <div className="user-name text-lg font-bold">{username}</div>
              </div>
            ) : (
              <div className="flex flex-col items-center justify-center">
                <div className="welcome-message text-mainColor font-bold pb-5">
                  Welcome to Cryptoptima!
                </div>
                <div className="login-btn w-full">
                  <MainButton
                    name="Login"
                    hoverScale={true}
                    hoverBg={true}
                    onClick={() => {
                      setLoginSignup("login");
                      setUserModal(true);
                    }}
                  />
                </div>
                <div className="signup-btn text-sm pt-2.5">
                  No account?{" "}
                  <span
                    className="text-mainColor cursor-pointer hover:text-mainUpColor"
                    onClick={() => {
                      setLoginSignup("signup");
                      setUserModal(true);
                    }}
                  >
                    Create one
                  </span>
                </div>
              </div>
            )}
          </div>
          {isLogin && (
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
          )}

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
          {isLogin && (
            <div className="signout-btn py-5 text-[15px] border-borderColor border-t-[1px] cursor-pointer hover:text-mainDownColor hidden">
              Sign Out
            </div>
          )}
        </div>
        <Modal isOpen={userModal} setIsOpen={setUserModal}>
          <div className="user-modal-container flex flex-col gap-2.5 justify-center items-center">
            <div className="user-modal-comment text-2xl font-semibold">
              {loginSignup === "login" ? "Welcome Back!" : "Create an Account"}
            </div>
            {loginSignup === "login" && (
              <div className="user-modal-subcomment text-[#72717d] text-sm text-center">
                Let's go see how much rebation you can get back right now.
              </div>
            )}
            {loginSignup === "signup" && (
              <div className="user-modal-subcomment text-[#72717d] text-sm text-center">
                Lowering trading costs is a basic of smart investing.
                <br />
                <span className="text-mainColor font-semibold">
                  Rebate the fees
                </span>{" "}
                you paid to crypto exchanges with{" "}
                <span className="text-mainColor font-semibold">
                  cryptoptima
                </span>
                !
              </div>
            )}
            <div className="social-login pt-7 pb-5">
              <ul className="flex gap-10">
                <li>
                  <Image
                    className="cursor-pointer"
                    src="image/logo/logo_facebook.svg"
                    alt="google"
                    width={50}
                    height={50}
                  />
                </li>
                <li>
                  <Link
                    href={`${process.env.NEXT_PUBLIC_SERVER_URL}/oauth2/authorization/google`}
                  >
                    <Image
                      className="cursor-pointer"
                      src="image/logo/logo_google.svg"
                      alt="google"
                      width={50}
                      height={50}
                    />
                  </Link>
                </li>
                <li>
                  <Image
                    className="cursor-pointer"
                    src="image/logo/logo_twitter.svg"
                    alt="google"
                    width={50}
                    height={50}
                  />
                </li>
              </ul>
            </div>
          </div>
        </Modal>
      </div>
    </div>
  );
};
