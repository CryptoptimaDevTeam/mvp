import { SetStateAction, useState } from "react";
import Image from "next/image";
import { MainButton, NormalButton, SubButton } from "../atoms/button";
import type { exchangeListType } from "../../data/registrationStatic";
import { HelpBox } from "../atoms/helpBox";
import { styled } from "styled-components";
import { useRouter } from "next/router";
import { Modal } from "../atoms/modal";

interface ProgressBarPropsType {
  width: number;
}

const ProgressBar = styled.div<ProgressBarPropsType>`
  width: ${(props) => `${props.width}%`};
`;

export const ExchangeCardVertical = (props: exchangeListType) => {
  const [userStaticHelp, setUserStaticHelp] = useState<boolean>(false);
  const [paybackStaticHelp, setPaybackStaticHelp] = useState<boolean>(false);
  const [registrationModal, setRegistrationModal] = useState<boolean>(false);
  const router = useRouter();

  const capacity =
    (props.exchangeUserCount / props.exchangePossibleUserCount) * 100;

  return (
    <div
      key={props.exchangeName}
      className="flex flex-col shadow-[0_5px_20px_0_rgba(11,7,110,.06)] rounded relative"
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
      <div className="exchange-user-summary pb-5 px-2.5">
        <UserCapacityBar capacity={capacity} />
      </div>
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
            className="payback-static-summary flex items-center gap-1 cursor-help text-textGrayColor text-sm"
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
            onClick={() => {
              setRegistrationModal(true);
            }}
            hoverBg={true}
            hoverScale={true}
          />
        )}
      </div>
      <RegistrationModal
        registrationModal={registrationModal}
        setRegistrationModal={setRegistrationModal}
        exchangeName={props.exchangeName}
      />
    </div>
  );
};

export const ExchangeCardHorizontal = (props: exchangeListType) => {
  const [userStaticHelp, setUserStaticHelp] = useState<boolean>(false);
  const [paybackStaticHelp, setPaybackStaticHelp] = useState<boolean>(false);
  const [registrationModal, setRegistrationModal] = useState<boolean>(false);
  const router = useRouter();

  const capacity =
    (props.exchangeUserCount / props.exchangePossibleUserCount) * 100;

  return (
    <div
      key={props.exchangeName}
      className="flex gap-2.5 shadow-[0_5px_20px_0_rgba(11,7,110,.06)] rounded relative"
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

        <div className="exchange-user-summary pb-5 px-2.5">
          <UserCapacityBar capacity={capacity} />
        </div>

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
                upsideDown="true"
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
                upsideDown="true"
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

        <div className="Cryptoptima-area flex flex-col px-2.5 border-[2px] my-[-4px] border-mainColor rounded">
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
            onClick={() => {
              router.push(`/exchange/${props.exchangeName}`);
            }}
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
              onClick={() => {
                setRegistrationModal(true);
              }}
              hoverBg={true}
              hoverScale={true}
              style="px-5 text-sm w-full h-[60px]"
            />
          )}
        </div>
      </div>
      <RegistrationModal
        registrationModal={registrationModal}
        setRegistrationModal={setRegistrationModal}
        exchangeName={props.exchangeName}
      />
    </div>
  );
};

interface UserCapacityBarPropsType {
  capacity: number;
}

const UserCapacityBar = ({ capacity }: UserCapacityBarPropsType) => {
  return (
    <div className="w-full h-[12px] border-[1px] border-borderColor rounded-full relative overflow-hidden flex justify-center items-center">
      <ProgressBar
        className={`${
          capacity <= 10
            ? "bg-green-800"
            : capacity <= 20
            ? "bg-green-700"
            : capacity <= 30
            ? "bg-green-600"
            : capacity <= 40
            ? "bg-green-500"
            : capacity <= 50
            ? "bg-yellow-400"
            : capacity <= 60
            ? "bg-yellow-500"
            : capacity <= 70
            ? "bg-orange-400"
            : capacity <= 80
            ? "bg-orange-600"
            : capacity <= 90
            ? "bg-red-500"
            : "bg-red-700"
        } h-[26px] w-[100%] rounded-l-full ${
          capacity < 100 ? "rounded-r-none" : "rounded-r-full"
        } animate-gage anim absolute left-0`}
        width={capacity}
      />
    </div>
  );
};

interface RegistrationModalPropsType {
  exchangeName: string;
  registrationModal: boolean;
  setRegistrationModal: React.Dispatch<React.SetStateAction<boolean>>;
}

const RegistrationModal = ({
  exchangeName,
  registrationModal,
  setRegistrationModal,
}: RegistrationModalPropsType) => {
  const router = useRouter();

  return (
    <Modal
      isOpen={registrationModal}
      setIsOpen={setRegistrationModal}
      modalWidth="w-[600px]"
    >
      <div className="registration-modal-container flex flex-col gap-5 justify-center items-center">
        <div className="registration-modal-comment text-3xl font-semibold">
          Register for Payback on {exchangeName.toUpperCase()}
        </div>

        <div className="registration-modal-subcomment text-[#72717d] text-sm text-center w-[500px]">
          With {exchangeName.toUpperCase()}, you can save up to 70% on fees
          through the Cryptoptima payback service.
        </div>
        <div className="registration-modal-btn flex gap-5 w-[500px] pt-5">
          <div className="registration-no-account-btn flex-[1_1_0%]">
            <SubButton
              name={`No ${exchangeName.toUpperCase()} Account`}
              onClick={() => {
                router.push(`/registration/${exchangeName}/no-account`);
              }}
              style="w-full h-[50px]"
              hoverScale={true}
            />
          </div>
          <div className="registration-already-account-btn flex-[1_1_0%]">
            <MainButton
              name={`Existing ${exchangeName.toUpperCase()} Account`}
              onClick={() => {
                router.push(`/registration/${exchangeName}/existing-account`);
              }}
              style="w-full h-[50px]"
              hoverScale={true}
              hoverBg={true}
            />
          </div>
        </div>
      </div>
    </Modal>
  );
};
