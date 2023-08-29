import { useState } from "react";
import Image from "next/image";
import { MainButton, NormalButton, SubButton } from "../atoms/button";
import type { exchangeListType } from "../../data/registrationStatic";
import { HelpBox } from "../atoms/helpBox";

export const ExchangeCardVertical = (props: exchangeListType) => {
  const [userStaticHelp, setUserStaticHelp] = useState<boolean>(false);
  const [paybackStaticHelp, setPaybackStaticHelp] = useState<boolean>(false);

  return (
    <div
      key={props.exchangeName}
      className="flex flex-col shadow-[0_5px_20px_0_rgba(11,7,110,.06)] rounded "
    >
      <div className="exchange-logo rounded-t overflow-hidden">
        <Image
          src={props.exchangeImgUrl}
          alt={props.exchangeName}
          width={500}
          height={500}
          priority={true}
        />
      </div>
      <div className="exchange-benefit-summary px-2.5 py-5 flex flex-col">
        <div className="expiration text-sm text-textGrayColor pb-1">
          Indefinitely
        </div>
        <div className="payback-benefit font-semibold">
          <span
            className={`${
              props.exchangeUserCount >= props.exchangePossibleUserCount
                ? "text-redColor"
                : "text-mainColor"
            } font-bold text-xl`}
          >
            {props.exchangePaybackRate}%
          </span>{" "}
          Commission Payback
        </div>
        <div className="fee-discount-benefit font-semibold text-right">
          +{" "}
          <span
            className={`${
              props.exchangeUserCount >= props.exchangePossibleUserCount
                ? "text-redColor"
                : "text-mainColor"
            } font-bold text-xl`}
          >
            {props.exchangeFeeDiscountRate}%
          </span>{" "}
          Discount on Trading Fees
        </div>
      </div>
      <div className="exchange-user-summary"></div>
      <div className="exchange-static-summary flex gap-2.5 px-2.5 pb-2.5">
        <div className="exchange-user-static-wrapper relative">
          <div
            className="user-static flex items-center gap-1 cursor-help text-textGrayColor text-sm"
            onMouseEnter={() => {
              setUserStaticHelp(true);
            }}
            onMouseLeave={() => {
              setUserStaticHelp(false);
            }}
          >
            <div className="user-icon">
              <Image
                src="/image/icon/icon_user_mainColor.svg"
                alt="registrants"
                width={20}
                height={20}
              />
            </div>
            <div className="user-count">
              {props.exchangeUserCount.toLocaleString("ko-KR")}
            </div>
          </div>

          <div className="user-static-explain absolute top-[-80px] rounded-md">
            <HelpBox
              isOpen={userStaticHelp}
              text="The number of registered traders|(aggregated until April 2023)"
              bottom="-9px"
              left="10px"
            />
          </div>
        </div>

        <div className="exchange-payback-static-wrapper relative">
          <div
            className="payback-static-summary flex items-center gap-1 cursor-help text-textGrayColor text-sm           onMouseEnter={() => {
            setPaybackStaticHelp(true);
          }}
          onMouseLeave={() => {
            setPaybackStaticHelp(false);
          }}"
            onMouseEnter={() => {
              setPaybackStaticHelp(true);
            }}
            onMouseLeave={() => {
              setPaybackStaticHelp(false);
            }}
          >
            <div className="tether-icon">
              <Image
                src="/image/logo/logo_tether.svg"
                alt="payback amount"
                width={20}
                height={20}
              />
            </div>
            <div className="payback-amount">
              {props.exchangeAvgPaybackAmount.toLocaleString("ko-KR")}
            </div>
          </div>
          <div className="payback-static-detail absolute top-[-80px] left-[-105px] rounded-md">
            <HelpBox
              isOpen={paybackStaticHelp}
              text="Average monthly USDT rebate per person|(aggregated until April
              2023)"
              bottom="-9px"
              left="110px"
            />
          </div>
        </div>
      </div>
      <div className="exchange-register-btn p-2.5">
        {props.exchangeUserCount >= props.exchangePossibleUserCount ? (
          <NormalButton
            className="exchange-register-impossible-btn"
            name="Registration is full 🔥"
            onClick={() => {}}
            disabled={
              props.exchangeUserCount >= props.exchangePossibleUserCount
            }
            style="cursor-not-allowed text-white bg-redColor rounded-lg"
          />
        ) : (
          <MainButton
            className="exchange-register-possible-btn"
            name="Register Payback"
            onClick={() => {}}
            hoverBg={true}
            hoverScale={true}
          />
        )}
      </div>
    </div>
  );
};

export const ExchangeCardHorizontal = (props: exchangeListType) => {
  const [userStaticHelp, setUserStaticHelp] = useState<boolean>(false);
  const [paybackStaticHelp, setPaybackStaticHelp] = useState<boolean>(false);

  return (
    <div
      key={props.exchangeName}
      className="flex gap-2.5 shadow-[0_5px_20px_0_rgba(11,7,110,.06)] rounded "
    >
      <div className="exchange-logo rounded overflow-hidden">
        <Image
          src={props.exchangeImgUrl}
          alt={props.exchangeName}
          width={300}
          height={300}
          priority={true}
        />
      </div>

      <div className="exchange-info-area flex flex-col">
        <div className="exchange-benefit-summary px-2.5 py-5 flex flex-col">
          <div className="expiration text-sm text-textGrayColor pb-1">
            Indefinitely
          </div>
          <div className="payback-benefit font-semibold">
            <span
              className={`${
                props.exchangeUserCount >= props.exchangePossibleUserCount
                  ? "text-redColor"
                  : "text-mainColor"
              } font-bold text-xl`}
            >
              {props.exchangePaybackRate}%
            </span>{" "}
            Commission Payback
          </div>
          <div className="fee-discount-benefit font-semibold text-right">
            +{" "}
            <span
              className={`${
                props.exchangeUserCount >= props.exchangePossibleUserCount
                  ? "text-redColor"
                  : "text-mainColor"
              } font-bold text-xl`}
            >
              {props.exchangeFeeDiscountRate}%
            </span>{" "}
            Discount on Trading Fees
          </div>
        </div>

        <div className="exchange-user-summary"></div>

        <div className="exchange-static-summary flex gap-2.5 px-2.5 pb-2.5">
          <div className="exchange-user-static-wrapper relative">
            <div
              className="user-static flex items-center gap-1 cursor-help text-textGrayColor text-sm"
              onMouseEnter={() => {
                setUserStaticHelp(true);
              }}
              onMouseLeave={() => {
                setUserStaticHelp(false);
              }}
            >
              <div className="user-icon">
                <Image
                  src="/image/icon/icon_user_mainColor.svg"
                  alt="registrants"
                  width={20}
                  height={20}
                />
              </div>
              <div className="user-count">
                {props.exchangeUserCount.toLocaleString("ko-KR")}
              </div>
            </div>

            <div className="user-static-explain absolute bottom-[-80px] rounded-md">
              <HelpBox
                isOpen={userStaticHelp}
                text="The number of registered traders|(aggregated until April 2023)"
                top="-9px"
                left="10px"
                upsideDown={true}
              />
            </div>
          </div>

          <div className="exchange-payback-static-wrapper relative">
            <div
              className="payback-static-summary flex items-center gap-1 cursor-help text-textGrayColor text-sm           onMouseEnter={() => {
            setPaybackStaticHelp(true);
          }}
          onMouseLeave={() => {
            setPaybackStaticHelp(false);
          }}"
              onMouseEnter={() => {
                setPaybackStaticHelp(true);
              }}
              onMouseLeave={() => {
                setPaybackStaticHelp(false);
              }}
            >
              <div className="tether-icon">
                <Image
                  src="/image/logo/logo_tether.svg"
                  alt="payback amount"
                  width={20}
                  height={20}
                />
              </div>
              <div className="payback-amount">
                {props.exchangeAvgPaybackAmount.toLocaleString("ko-KR")}
              </div>
            </div>
            <div className="payback-static-detail absolute bottom-[-80px] left-[-105px] rounded-md">
              <HelpBox
                isOpen={paybackStaticHelp}
                text="Average monthly USDT rebate per person|(aggregated until April
              2023)"
                top="-9px"
                left="110px"
                upsideDown={true}
              />
            </div>
          </div>
        </div>
      </div>

      <div className="exchange-fee-comparison-area flex gap-2.5 text-sm py-2.5">
        <div className="division-area flex justify-between gap-2.5">
          <div className="spot-futures-sort flex flex-col">
            <div className="flex-[0.5_1_0%] flex items-center"></div>
            <div className="flex-[1_1_0%] flex justify-center items-center font-semibold">
              Spot
            </div>
            <div className="flex-[1_1_0%] flex justify-center items-center font-semibold">
              Futures
            </div>
          </div>
          <div className="limit-market-sort flex flex-col">
            <div className="flex-[0.5_1_0%] flex items-center"></div>
            <div className="spot-area flex-[1_1_0%] flex flex-col">
              <div className="flex items-center flex-[1_1_0%]">Maker</div>
              <div className="flex items-center flex-[1_1_0%]">Taker</div>
            </div>
            <div className="futures-area flex-[1_1_0%] flex flex-col">
              <div className="flex items-center flex-[1_1_0%]">Maker</div>
              <div className="flex items-center flex-[1_1_0%]">Taker</div>
            </div>
          </div>
        </div>

        <div className="regular-area flex flex-col">
          <div className="flex-[0.5_1_0%] flex items-center justify-center font-semibold">
            Regular
          </div>
          <div className="spot-area flex-[1_1_0%] flex flex-col">
            <div className="flex items-center flex-[1_1_0%]">Maker Fees</div>
            <div className="flex items-center flex-[1_1_0%]">Taker Fees</div>
          </div>
          <div className="futures-area flex-[1_1_0%] flex flex-col">
            <div className="flex items-center flex-[1_1_0%]">Maker Fees</div>
            <div className="flex items-center flex-[1_1_0%]">Taker Fees</div>
          </div>
        </div>

        <div className="referral-area flex flex-col">
          <div className="flex-[0.5_1_0%] flex items-center justify-center font-semibold">
            Referral
          </div>
          <div className="spot-area flex-[1_1_0%] flex flex-col">
            <div className="flex justify-center items-center flex-[1_1_0%]">
              Maker Fees
            </div>
            <div className="flex justify-center items-center flex-[1_1_0%]">
              Taker Fees
            </div>
          </div>
          <div className="futures-area flex-[1_1_0%] flex flex-col">
            <div className="flex justify-center items-center flex-[1_1_0%]">
              Maker Fees
            </div>
            <div className="flex justify-center items-center flex-[1_1_0%]">
              Taker Fees
            </div>
          </div>
        </div>

        <div className="Cryptoptima-area flex flex-col px-2.5 border-[2px] border-mainColor rounded">
          <div className="flex-[0.5_1_0%] flex items-center justify-center">
            <Image
              src="/image/logo/logo_full_l.svg"
              alt="logo"
              width={100}
              height={100}
            />
          </div>
          <div className="spot-area flex-[1_1_0%] flex flex-col">
            <div className="flex justify-center items-center flex-[1_1_0%]">
              Maker Fees
            </div>
            <div className="flex justify-center items-center flex-[1_1_0%]">
              Taker Fees
            </div>
          </div>
          <div className="futures-area flex-[1_1_0%] flex flex-col">
            <div className="flex justify-center items-center flex-[1_1_0%]">
              Maker Fees
            </div>
            <div className="flex justify-center items-center flex-[1_1_0%]">
              Taker Fees
            </div>
          </div>
        </div>
      </div>

      <div className="exchange-relate-btn-area flex flex-col justify-evenly items-center pr-2.5">
        <div className="exchange-explain-btn w-full">
          <SubButton
            className="exchange-detail-btn"
            name="Exchange Details"
            onClick={() => {}}
            hoverScale={true}
            style="px-5 text-sm w-full h-[60px]"
          />
        </div>
        <div className="exchange-register-btn w-full">
          {props.exchangeUserCount >= props.exchangePossibleUserCount ? (
            <NormalButton
              className="exchange-register-impossible-btn"
              name="Registration is full 🔥"
              onClick={() => {}}
              disabled={
                props.exchangeUserCount >= props.exchangePossibleUserCount
              }
              style="cursor-not-allowed text-white bg-redColor rounded-lg px-5 text-sm w-full h-[60px]"
            />
          ) : (
            <MainButton
              className="exchange-register-possible-btn"
              name="Payback Registration"
              onClick={() => {}}
              hoverBg={true}
              hoverScale={true}
              style="px-5 text-sm w-full h-[60px]"
            />
          )}
        </div>
      </div>
    </div>
  );
};
