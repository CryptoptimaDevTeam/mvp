import { useState } from 'react';
// import PageTransitionWrapper from '../../components/atoms/pageEffect';
import { MainButton } from '../../components/atoms/button';
import Image from 'next/image';

const PreRegister = () => {
  const [currentPage, setCurrentPage] = useState<number>(1);

  return (
    <div className="landing-page min-h-[100vh] flex flex-col justify-between items-center">
      <LandingPageHeader />
      <main>
        {currentPage === 1 && <FirstLandingPage />}
        {currentPage === 2 && <SecondLandingPage />}
        {currentPage === 3 && <SecondLandingPage />}
      </main>

      <LandingPageFooter />
    </div>
  );
};

export default PreRegister;

const LandingPageHeader = () => {
  return (
    <header className="w-full flex justify-center items-start pt-[50px] h-[200px]">
      <Image
        src="/image/logo/logo_full_l.svg"
        alt="logo"
        width={250}
        height={250}
      />
    </header>
  );
};

const LandingPageFooter = () => {
  return (
    <footer className="w-full flex justify-center items-start h-[200px]">
      <div></div>
    </footer>
  );
};

const FirstLandingPage = () => {
  return (
    <div>
      <div>첫 번째 페이지</div>
    </div>
  );
};

const SecondLandingPage = () => {
  const homeSectionStyle: string =
    'max-w-[1248px] mx-auto flex justify-center items-center';

  return (
    <div>
      <section className={`home-section1 ${homeSectionStyle} flex-col`}>
        <div className="section-title text-[30px] font-bold text-center px-6">
          Rebate your crypto trading fees with
          <br />
          Cryptoptima's Payback Service
        </div>
        <div className="section-body mt-8 text-[16px] max-w-[740px] text-[#72717d] text-center">
          Stop giving away your money to crypto exchanges and referrals.{' '}
          <span className="text-mainColor">Register our payback service</span>{' '}
          and take advantage of not only{' '}
          <span className="text-mainColor">maximum trading fee discounts</span>{' '}
          but also{' '}
          <span className="text-mainColor">
            up to 99% referral bonus rebate
          </span>
          !
        </div>
        <form>
          <div className="emain-input">
            <label></label>
            <input type="text" />
          </div>
          <div className="exchange-input"></div>
          <div className="type-input"></div>

          <div className="register-btn mt-10">
            <MainButton
              name="Apply for pre-registration"
              onClick={() => {}}
              style="py-[21px] px-[24px] w-[250px]"
              hoverScale={true}
              hoverBg={true}
            />
          </div>
        </form>
      </section>
    </div>
  );
};
