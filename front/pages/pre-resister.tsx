import { ReactElement } from 'react';
import NoLayout from '../layout/noLayout';
import { MainButton } from '../components/atoms/button';
import Image from 'next/image';

const PreResister = () => {
  const homeSectionStyle: string =
    'max-w-[1248px] mx-auto flex justify-center items-center';

  return (
    <div>
      <section className='w-full flex justify-center items-center h-[200px]'>
        <Image
          src='/image/logo/logo_full_l.svg'
          alt='logo'
          width={250}
          height={250}
        />
      </section>
      <section className={`home-section1 ${homeSectionStyle} flex-col `}>
        <div className='section-title text-[30px] font-bold text-center px-6'>
          Rebate your crypto trading fees with
          <br />
          Cryptoptima's Payback Service
        </div>
        <div className='section-body mt-8 text-[16px] max-w-[740px] text-[#72717d] text-center'>
          Stop giving away your money to crypto exchanges and referrals.{' '}
          <span className='text-mainColor'>Register our payback service</span>{' '}
          and take advantage of not only{' '}
          <span className='text-mainColor'>maximum trading fee discounts</span>{' '}
          but also{' '}
          <span className='text-mainColor'>
            up to 99% referral bonus rebate
          </span>
          !
        </div>
        <form>
          <div className='emain-input'>
            <label></label>
            <input type='text' />
          </div>
          <div className='exchange-input'></div>
          <div className='type-input'></div>

          <div className='register-btn mt-10'>
            <MainButton
              name='Rebate crypto trading fees'
              onClick={() => {}}
              style='py-[21px] px-[24px] w-[250px]'
              hoverScale={true}
              hoverBg={true}
            />
          </div>
        </form>
      </section>
    </div>
  );
};

export default PreResister;

PreResister.getLayout = function getLayout(page: ReactElement) {
  return <NoLayout>{page}</NoLayout>;
};
