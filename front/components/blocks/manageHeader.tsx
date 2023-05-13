import { useEffect } from "react";
import Image from "next/image";
import { SubButton } from "../atoms/button";
import { useRouter } from "next/router";
import Link from "next/link";
import path from "path";

export const ManageHeader = () => {
  const router = useRouter();
  const { pathname } = router;

  const topNavData = [
    {
      order: 1,
      className: "management-account",
      name: "Account",
      href: "/management/account",
      width: "w-[76px]",
    },
    {
      order: 2,
      className: "management-account",
      name: "Payback",
      href: "/management/payback",
      width: "w-[77px]",
    },
    {
      order: 3,
      className: "management-account",
      name: "Crypto Data",
      href: "/management/cryptodata",
      width: "w-[102px]",
    },
    {
      order: 4,
      className: "management-account",
      name: "Performance",
      href: "/management/performance",
      width: "w-[108px]",
    },
  ];

  const navigationClassName: string =
    "block text-sm transition-all duration-300 ease-in-out hover:text-mainDownColor py-5 px-2.5";
  return (
    <div className="header-componentainer sticky top-0 z-50">
      <header className="header-wrapper flex h-[70px] px-6 justify-between items-center shadow-[0_5px_20px_0_rgba(11,7,110,.04)] bg-white">
        <div className="header-left flex items-center gap-10">
          <div className="logo-area">
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
              {topNavData.map((el) => (
                <li className={`${el.className} cursor-pointer`} key={el.order}>
                  <Link
                    href={el.href}
                    className={`${navigationClassName} ${
                      el.width
                    } text-center ${
                      el.href === pathname && "text-mainColor font-semibold"
                    }`}
                  >
                    {el.name}
                  </Link>
                </li>
              ))}
            </ul>
          </nav>
        </div>
        <div className="header-right flex justify-end gap-5">
          <div className="manager-name text-sm whitespace-nowrap flex items-center">
            Hello,
            <span className="text-mainColor font-semibold pl-1">
              {"Jeong Woo Park"}
            </span>
            !
          </div>
          <SubButton
            name="Log out"
            hoverScale={true}
            style="text-sm px-5"
            onClick={() => {}}
          />
        </div>
      </header>
    </div>
  );
};
