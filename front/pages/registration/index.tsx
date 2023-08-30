import { useState } from "react";
import Image from "next/image";
import { Carousel } from "../../components/atoms/carousel";
import { carouselList, exchangeList } from "../../data/registrationStatic";
import { ExchangeCardVertical } from "../../components/blocks/card";
import { exchangeFeeData } from "../../data/homeStatic";
import { RegistrationModal } from "../../components/blocks/card";

type comparisonMode = "Spot" | "Derivatives";

export default function RegistrationMain() {
  const [comparisonMode, setComparisonMode] =
    useState<comparisonMode>("Derivatives");
  const [selectedExchange, setSelectedExchange] = useState<string>("none");
  const [registrationModal, setRegistrationModal] = useState<boolean>(false);

  const rgmainSectionStyle: string =
    "max-w-[1248px] mx-auto flex justify-center items-center";

  const comparisonTableCommonStyle: string =
    "text-[16px] font-medium flex-[1_1_auto] flex justify-center items-center";
  return (
    <div>
      <section
        className={`rgmain-section1 pb-[100px] flex justify-center items-center w-full`}
      >
        <Carousel carouselList={carouselList} />
      </section>
      <section className={`rgmain-section2 ${rgmainSectionStyle} pb-[100px]`}>
        <div>
          <ul className="flex gap-10">
            {exchangeList.map((el) => (
              <li key={el.exchangeName}>
                <ExchangeCardVertical {...el} />
              </li>
            ))}
          </ul>
        </div>
      </section>
      <section
        className={`rgmain-section3 max-w-[1248px] mx-auto pt-[60px] pb-[100px]`}
      >
        <div className="section-title text-[32px] font-bold text-center">
          Exchange Trading Fee Comparison
        </div>
        <div className="mt-8 text-[20px] max-w-[960px] text-[#72717d] text-center mx-auto">
          Prevent money from flowing out of your account by trading with the
          lowest fees, in addition to the bonus provided upon sign-up through
          referrals!
        </div>
        <div className="fee-comparison-table-wrapper pt-[80px]">
          <div className="fee-comparison-table-header w-full flex justify-between items-center">
            <div className="comparison-mode-title text-[26px] font-bold">
              {comparisonMode}
            </div>
            <div className="comparison-mode-controller cursor-pointer">
              <div className="comparison-mode-button-wrapper flex border-[1px] border-borderColor rounded-lg">
                <div
                  className={`spot-mode-btn w-[120px] text-center text-[14px] h-full py-2 ${
                    comparisonMode === "Spot"
                      ? "text-white bg-mainColor rounded-tl-lg rounded-bl-lg"
                      : ""
                  } transition-all duration-300`}
                  onClick={() => setComparisonMode("Spot")}
                >
                  Spot
                </div>
                <div
                  className={`derivatives-mode-btn w-[120px] text-center text-[14px] py-2 ${
                    comparisonMode === "Derivatives"
                      ? "text-white bg-mainColor rounded-tr-lg rounded-br-lg"
                      : ""
                  } transition-all duration-300`}
                  onClick={() => setComparisonMode("Derivatives")}
                >
                  Derivatives
                </div>
              </div>
            </div>
          </div>
          <div className="fee-comparison-table-content pt-5">
            <div className="table-header grid grid-cols-4 border-y-[1px] border-borderColor py-2.5">
              <div className={`${comparisonTableCommonStyle}`}>Exchange</div>
              <div
                className={`text-[16px] font-medium flex-[1_1_auto] flex flex-col`}
              >
                <div className="flex justify-center">Normal</div>
                <div className="grid grid-cols-2 pt-2">
                  <div className="text-xs text-center">Maker Fees</div>
                  <div className="text-xs text-center">Taker Fees</div>
                </div>
              </div>
              <div
                className={`text-[16px] font-medium flex-[1_1_auto] flex flex-col`}
              >
                <div className="flex justify-center">Referral</div>
                <div className="grid grid-cols-2 pt-2">
                  <div className="text-xs text-center">Maker Fees</div>
                  <div className="text-xs text-center">Taker Fees</div>
                </div>
              </div>
              <div
                className={`text-[16px] font-medium flex-[1_1_auto] flex flex-col`}
              >
                <div className="flex justify-center">
                  <Image
                    src="/image/logo/logo_full_l.svg"
                    alt="logo"
                    width={120}
                    height={150}
                  />
                </div>
                <div className="grid grid-cols-2 pt-2">
                  <div className="text-xs text-center">Maker Fees</div>
                  <div className="text-xs text-center">Taker Fees</div>
                </div>
              </div>
            </div>
            <div className="table-body">
              {exchangeFeeData.map((el) => (
                <div
                  className="grid grid-cols-4 border-b-[1px] border-borderColor py-5 hover:bg-[#f5f5f5] cursor-pointer"
                  key={el.exchangeName}
                  onClick={() => {
                    setSelectedExchange(el.exchangeName);
                    setRegistrationModal(true);
                  }}
                >
                  <div className={`${comparisonTableCommonStyle}`}>
                    <div>
                      <Image
                        src={el.exchangeImgUrl}
                        alt={`${el.exchangeName} logo`}
                        width={30}
                        height={30}
                      />
                    </div>
                    <div className="w-[100px] pl-3 font-semibold">
                      {el.exchangeName}
                    </div>
                  </div>
                  <div className={`grid grid-cols-2`}>
                    <div className="text-center">
                      {comparisonMode === "Spot"
                        ? el.normalSpotLimit
                        : el.normalDerivativesLimit}
                      %
                    </div>
                    <div className="text-center">
                      {comparisonMode === "Spot"
                        ? el.normalSpotMarket
                        : el.normalDerivativesMarket}
                      %
                    </div>
                  </div>
                  <div className={`grid grid-cols-2`}>
                    <div className="text-center">
                      {comparisonMode === "Spot"
                        ? el.referralSpotLimit
                        : el.referralDerivativesLimit}
                      %
                    </div>
                    <div className="text-center">
                      {comparisonMode === "Spot"
                        ? el.referralSpotMarket
                        : el.referralDerivativesMarket}
                      %
                    </div>
                  </div>
                  <div className={`grid grid-cols-2`}>
                    <div className="text-center text-mainColor font-bold">
                      {comparisonMode === "Spot"
                        ? el.cryptoptimaSpotLimit
                        : el.cryptoptimaDerivativesLimit}
                      %
                    </div>
                    <div className="text-center text-mainColor font-bold">
                      {comparisonMode === "Spot"
                        ? el.cryptoptimaSpotMarket
                        : el.cryptoptimaDerivativesMarket}
                      %
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>
          <RegistrationModal
            registrationModal={registrationModal}
            setRegistrationModal={setRegistrationModal}
            exchangeName={selectedExchange}
          />
        </div>
      </section>
    </div>
  );
}
