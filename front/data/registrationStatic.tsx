import type { CarouselListType } from "../components/atoms/carousel";

export const carouselList: Array<CarouselListType> = [
  {
    RC: (
      <div className="h-[400px] flex flex-col justify-center items-center bg-[#f5f5f5]">
        <div className="text-center text-[36px] font-bold">
          With CryptoOptima, <span className="text-mainColor">130,000+</span>{" "}
          traders around the world <br />
          have been get an average of{" "}
          <span className="text-mainColor">$629 per month</span> in trading fees
          back!
        </div>
      </div>
    ),
  },
  {
    RC: (
      <div className="h-[400px] flex flex-col justify-center items-center bg-[#f5f5f5]">
        <div className="flex flex-col items-center justify-center relative">
          <div className="text-center text-[36px] font-bold">
            New registrant can get{" "}
            <span className="text-mainColor">50 USDT</span> or <br />
            a&nbsp;
            <span className="text-mainColor">
              cryptocurrency random box
            </span>{" "}
            immediately
          </div>
          <div className="pt-10 text-[20px] font-semibold absolute top-[-80px] text-[#72717d]">
            2023.06.01 ~ 2023.06.30
          </div>
        </div>
      </div>
    ),
  },
  {
    RC: (
      <div className="h-[400px] flex flex-col justify-center items-center bg-[#f5f5f5]">
        <div className="flex flex-col items-center justify-center relative">
          <div className="pt-10 text-[20px] font-semibold absolute top-[-80px] text-[#72717d]">
            2023.06.12 ~ 2023.12.31
          </div>
          <div className="text-center text-[36px] font-bold">
            To celebrate Cryptoptima's 1st anniversary
            <br />
            <span className="text-mainColor">
              Free Tradingview subscription support event
            </span>{" "}
            for new registrants
          </div>
        </div>
      </div>
    ),
  },
];
