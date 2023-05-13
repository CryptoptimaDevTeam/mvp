import Image from "next/image";
import { MainButton, SubButton } from "../components/atoms/button";
import { HomeDropdown } from "../components/atoms/dropdown";
import { questionDropdownData } from "../data/homeStatic";

export default function Home() {
  const homeSectionStyle: string =
    "max-w-[1248px] mx-auto py-[100px] flex justify-center items-center";

  return (
    <div>
      <section
        className={`home-section1 ${homeSectionStyle} flex-col pt-[60px]`}
      >
        <div className="section-title text-[48px] font-bold text-center max-w-[860px] px-6">
          Rebate your crypto trading fees with Cryptoptima's Payback Service
        </div>
        <div className="section-body mt-8 text-[20px] max-w-[886px] text-[#72717d] text-center">
          Stop giving away your money to crypto exchanges and referrals.
          <span className="text-mainColor">
            Register our payback service
          </span>{" "}
          and take advantage of not only{" "}
          <span className="text-mainColor">maximum trading fee discounts</span>{" "}
          but also{" "}
          <span className="text-mainColor">
            up to 99% referral bonus rebate
          </span>
          !
        </div>
        <div className="register-btn mt-10">
          <MainButton
            name="Rebate crypto trading fees"
            onClick={() => {}}
            style="py-[21px] px-[24px] w-[250px]"
            hoverScale={true}
            hoverBg={true}
          />
        </div>
      </section>
      <div className="bg-[#f5f5f5]">
        <section
          className={`home-section2 ${homeSectionStyle} pt-[120px] pb-[80px] gap-10`}
        >
          <div className="comment-area flex-[3_1_0%] flex flex-col justify-center items-start">
            <div className="section-title text-[32px] font-bold">
              The starting point for smart investing is lowering transaction
              costs
            </div>
            <div className="section-body mt-5 text-[16px] text-[#72717d]">
              A seemingly small trading fee can make a big difference in your
              cumulative returns. Especially for traders with high-frequency
              trading, a difference in trading fees of as little as 0.01% can
              make or break an investment.
            </div>
            <div className="register-btn flex justify-center items-center mt-10">
              {" "}
              <MainButton
                name="Minimizing transaction fees"
                onClick={() => {}}
                style="py-[15px] px-[18px] w-[220px] text-sm"
                hoverScale={true}
                hoverBg={true}
              />
            </div>
          </div>
          <div className="content-area bg-slate-300 flex-[4_1_0%] h-[500px]"></div>
        </section>

        <section
          className={`home-section3 ${homeSectionStyle} gap-10 pt-[80px] pb-[120px]`}
        >
          <div className="content-area bg-slate-300 flex-[4_1_0%] h-[500px]"></div>
          <div className="comment-area flex-[3_1_0%] flex flex-col justify-center">
            <div className="section-title text-[32px] font-bold">
              With Cryptooptima, you can maximize your returns by minimizing
              transaction fees
            </div>
            <div className="section-body mt-5 text-[16px] text-[#72717d]">
              Our payback program allows you to trade with the lowest fees on
              each cryptocurrency exchange than any other method. 700,000+
              crypto traders around the world are already taking advantage of
              this.
            </div>
            <div className="register-btn flex justify-start items-center mt-10 gap-5">
              <SubButton
                name="Checking expected payback"
                onClick={() => {}}
                style="py-[15px] px-[18px] w-[220px] text-sm"
                hoverScale={true}
              />
              <MainButton
                name="Minimizing transaction fees"
                onClick={() => {}}
                style="py-[15px] px-[18px] w-[220px] text-sm"
                hoverScale={true}
                hoverBg={true}
              />
            </div>
          </div>
        </section>
      </div>

      <section className={`home-section4 ${homeSectionStyle} flex-col`}>
        <div className="section-title text-[48px] font-bold text-center max-w-[860px] px-6">
          How is it possible?
        </div>
        <div className="section-body mt-8 text-[20px] max-w-[886px] text-[#72717d] text-center">
          A service that rebate most of the cryptocurrency exchanges' referral
          revenue to traders.
        </div>
        <div className="section-content w-full mt-10 flex justify-center">
          <Image
            src="/image/home/payback_structure_EN.png"
            alt="payback structure"
            width={1000}
            height={600}
          />
        </div>
      </section>

      <section
        className={`home-section5 py-[100px] pb-[120px] mt-6 bg-[#f5f5f5]`}
      >
        <div className="max-w-[1248px] mx-auto flex justify-center items-center flex-col">
          <div className="section-title text-[48px] font-bold text-center max-w-[1100px] px-6">
            Cryptoptima is a collective of smart investors who are building
            economic freedom
          </div>
          <div className="section-body mt-8 text-[20px] max-w-[886px] text-[#72717d] text-center">
            It is not a platform that monetizes crypto exchanges' affiliate
            program. It is a gathering place for smart investors to collectively
            build an optimal trading environment. The more participants we have,
            the more we can negotiate with exchanges to lower fees.
          </div>
          <div className="register-btn mt-10 flex gap-10 w-[600px]">
            <SubButton
              name="Sharing this with your friends"
              onClick={() => {}}
              style="py-[21px] px-[24px]"
              hoverScale={true}
            />
            <MainButton
              name="Becominga a smart investor"
              onClick={() => {}}
              style="py-[21px] px-[24px]"
              hoverScale={true}
              hoverBg={true}
            />
          </div>
        </div>
      </section>
      <section className={`home-section6 ${homeSectionStyle} flex-col`}>
        <div className="section-title text-[48px] font-bold text-center max-w-[860px] px-6">
          Do you wonder about these points?
        </div>
        <div className="question-dropdown-area pt-[80px] flex">
          <ul className="question-dropdown-list flex flex-col gap-5">
            {questionDropdownData.map((el, idx) => (
              <HomeDropdown
                key={idx}
                order={el.order}
                title={el.title}
                body={el.body}
              />
            ))}
          </ul>
        </div>
      </section>
    </div>
  );
}
