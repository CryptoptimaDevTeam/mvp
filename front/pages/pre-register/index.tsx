import { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import ReactTyped from 'react-typed';
// import PageTransitionWrapper from '../../components/atoms/pageEffect';
import { Bar } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import type { ChartData, ChartOptions } from 'chart.js';

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

import Image from 'next/image';
import { MainButton, SubButton } from '../../components/atoms/button';
import { LoadingIndicator } from '../../components/atoms/loadingIndicator';

const PreRegister = () => {
  const [currentPage, setCurrentPage] = useState<number>(1);

  return (
    <div className='landing-page min-h-[100vh] flex flex-col justify-between items-center'>
      <LandingPageHeader />
      <main
        className='w-full flex justify-center items-center'
        style={{ height: 'calc(100vh - 200px)' }}
      >
        {currentPage === 1 && (
          <FirstLandingPage setCurrentPage={setCurrentPage} />
        )}
        {currentPage === 2 && (
          <SecondLandingPage setCurrentPage={setCurrentPage} />
        )}
        {currentPage === 3 && (
          <ThirdLandingPage setCurrentPage={setCurrentPage} />
        )}
      </main>

      <LandingPageFooter />
    </div>
  );
};

export default PreRegister;

const LandingPageHeader = () => {
  return (
    <header className='w-full flex justify-center items-end h-[100px]'>
      <Image
        src='/image/logo/logo_full_l.svg'
        alt='logo'
        width={200}
        height={200}
      />
    </header>
  );
};

const LandingPageFooter = () => {
  const OpenWebPage = (url: string) => {
    window.open(url, '_blank', 'noopener, noreferrer');
  };

  const snsList = [
    {
      name: 'youtube',
      url: 'https://www.youtube.com/@Cryptoptima_official',
    },
    {
      name: 'twitter',
      url: 'https://twitter.com/CryptoptimaOFFL',
    },
    {
      name: 'telegram',
      url: 'https://t.me/cryptoptima',
    },
  ];

  return (
    <footer className='w-full flex justify-center items-start h-[100px]'>
      <div className='logo-area flex justify-center items-center gap-7'>
        {snsList.map((el) => (
          <Image
            key={el.name}
            src={`/image/logo/logo_${
              el.name === 'twitter' ? `${el.name}_New` : el.name
            }.svg`}
            alt={`${name} logo`}
            className='cursor-pointer'
            width={30}
            height={30}
            onClick={() => {
              OpenWebPage(el.url);
            }}
          />
        ))}
      </div>
    </footer>
  );
};

interface LandingPageProps {
  setCurrentPage: React.Dispatch<React.SetStateAction<number>>;
}

const FirstLandingPage = ({ setCurrentPage }: LandingPageProps) => {
  const [showButton, setShowButton] = useState(false);

  useEffect(() => {
    const timer = setTimeout(() => {
      setShowButton(true);
    }, 4000);

    return () => {
      clearTimeout(timer);
    };
  }, []);

  return (
    <div className='w-full px-5 flex flex-col justify-start gap-[100px]'>
      <ReactTyped
        className='text-[40px] text-center font-bold'
        strings={[
          'Are you still not getting payback on exchange trading fees while trading crypto?',
        ]}
        typeSpeed={30}
      />
      <div
        className={`btn-area flex justify-center items-center transition-opacity duration-500 ${
          showButton ? 'opacity-100' : 'opacity-0'
        }`}
      >
        <MainButton
          name='Check Estimated Payback Amount'
          width='w-[340px]'
          style={`py-5 ${showButton ? 'cursor-pointer' : 'cursor-default'}`}
          onClick={() => {
            setCurrentPage(2);
          }}
          hoverBg={true}
        />
      </div>
    </div>
  );
};

interface paybackFactorType {
  exchange: string;
  capital: number;
  leverage: number;
  frequencyType: number;
}

const SecondLandingPage = ({ setCurrentPage }: LandingPageProps) => {
  const [paybackFactor, setPaybackFactor] = useState<paybackFactorType>({
    exchange: '',
    capital: 0,
    leverage: 0,
    frequencyType: 1,
  });
  const [showButton, setShowButton] = useState(false);
  const [currentSubPage, setCurrentSubPage] = useState<number>(1);
  const isFull =
    paybackFactor.exchange !== '' &&
    paybackFactor.capital > 0 &&
    paybackFactor.leverage >= 1 &&
    paybackFactor.frequencyType >= 1;

  useEffect(() => {
    const timer = setTimeout(() => {
      setShowButton(true);
    }, 3000);

    return () => {
      clearTimeout(timer);
    };
  }, []);

  useEffect(() => {
    if (currentSubPage !== 2) return;
    const timeout = setTimeout(() => {
      setCurrentSubPage(3);
    }, 1500);

    return () => {
      clearTimeout(timeout);
    };
  }, [currentSubPage]);

  const exchangeList: string[] = ['binance', 'bybit', 'okx', 'bitget'];
  const frequencyList: string[] = [
    'once a day or not',
    '1 to 2 times',
    '2 to 5 times',
    '5 to 10 times',
    '10 to 30 times',
    '30 to 50 times',
    'more than 50 times a day',
  ];

  return (
    <>
      {currentSubPage === 1 && (
        <section className='w-full px-5 flex flex-col justify-start gap-10'>
          <ReactTyped
            className='text-[40px] text-center font-bold'
            strings={[
              "Shall we find out how much money you've missed out on so far?",
            ]}
            typeSpeed={30}
          />
          <div
            className={`flex flex-col justify-center items-center transition-opacity duration-500 py-[40px] border-2 border-borderColor rounded-xl ${
              showButton ? 'opacity-100' : 'opacity-0'
            } gap-[60px]`}
          >
            <>
              <div className='exchange-select flex flex-col gap-5'>
                <div className='text-[20px] font-medium text-center'>
                  1. Choose only one exchange that you frequently use!
                </div>
                <div className='exchange-list flex gap-2.5'>
                  {exchangeList.map((el, idx) => (
                    <div
                      className={`p-2.5 border-[2px] rounded transition-border duration-500 ${
                        el === paybackFactor.exchange
                          ? 'border-mainColor'
                          : 'border-white'
                      }`}
                      onClick={() => {
                        setPaybackFactor((paybackFactor) => {
                          return { ...paybackFactor, exchange: el };
                        });
                      }}
                      key={idx}
                    >
                      <Image
                        src={`/image/logo/logo_${el}_full_w.png`}
                        alt={`${el} logo`}
                        width={200}
                        height={200}
                      />
                    </div>
                  ))}
                </div>
                <div className='flex justify-center'>
                  <input
                    type='checkbox'
                    id='etc'
                    onClick={() => {
                      if (paybackFactor.exchange === 'others') {
                        setPaybackFactor((paybackFactor) => {
                          return { ...paybackFactor, exchange: '' };
                        });
                      } else {
                        setPaybackFactor((paybackFactor) => {
                          return { ...paybackFactor, exchange: 'others' };
                        });
                      }
                    }}
                    checked={paybackFactor.exchange === 'others'}
                  />
                  <label
                    className='text-[16px] relative top-[-0.5px] pl-[5px] font-medium flex items-center'
                    htmlFor='etc'
                  >
                    Other Exchange
                  </label>
                </div>
              </div>

              <div className='w-full flex flex-col items-center gap-5'>
                <label
                  className='text-[20px] font-medium text-center'
                  htmlFor='capital'
                >
                  2. What scale of capital are you using for crypto trading?
                </label>
                <div className='relative'>
                  <input
                    type='number'
                    id='capital'
                    min='1'
                    className='border-[1px] border-borderColor rounded w-[300px] h-[50px] p-2.5'
                    onChange={(e) => {
                      setPaybackFactor((paybackFactor) => ({
                        ...paybackFactor,
                        capital: Number(e.target.value),
                      }));
                    }}
                  />
                  <div className='absolute top-[13px] right-4 font-semibold'>
                    USD
                  </div>
                </div>
              </div>

              <div className='w-full flex flex-col items-center gap-5'>
                <label
                  className='text-[20px] font-medium text-center'
                  htmlFor='capital'
                >
                  3. What is the typical leverage multiplier you use?
                </label>
                <div className='relative'>
                  <input
                    type='number'
                    id='capital'
                    min='1'
                    max='200'
                    className='border-[1px] border-borderColor rounded w-[300px] h-[50px] p-2.5 pl-8'
                    placeholder='1 ~ 200'
                    onChange={(e) => {
                      setPaybackFactor((paybackFactor) => ({
                        ...paybackFactor,
                        leverage: Number(e.target.value),
                      }));
                    }}
                  />
                  <div className='absolute top-[13px] left-4 font-semibold'>
                    X
                  </div>
                </div>
              </div>

              <div className='w-full flex flex-col items-center gap-5'>
                <label
                  className='text-[20px] font-medium text-center'
                  htmlFor='frequency'
                >
                  4. How frequently do you trade crypto on a daily basis?
                </label>
                <div className='relative'>
                  <select
                    className='border-[1px] border-borderColor rounded w-[300px] h-[50px] p-2.5'
                    id='frequency'
                    defaultValue={1}
                    onChange={(e) => {
                      setPaybackFactor((paybackFactor) => ({
                        ...paybackFactor,
                        frequencyType: Number(e.target.value),
                      }));
                    }}
                  >
                    {frequencyList.map((el, idx) => (
                      <option value={idx + 1}>{el}</option>
                    ))}
                  </select>
                </div>
              </div>

              <MainButton
                name='Check Estimated Payback Amount'
                width='w-[300px]'
                style={`py-5 ${
                  showButton
                    ? isFull
                      ? 'cursor-pointer'
                      : 'cursor-not-allowed'
                    : 'cursor-default'
                } `}
                onClick={() => {
                  setCurrentSubPage(2);
                }}
                hoverBg={true}
                disabled={!isFull}
              />
            </>
          </div>
        </section>
      )}

      {currentSubPage === 2 && (
        <section className='w-full h-full flex flex-col justify-center items-center relative'>
          <div className='w-full text-[40px] text-center font-bold absolute top-[30%]'>
            Calculating the estimated payback amount through AI.
          </div>
          <div>
            <LoadingIndicator />
          </div>
        </section>
      )}

      {currentSubPage === 3 && (
        <div className='w-full h-full flex flex-col justify-center items-center gap-20'>
          <div className='w-[1080px] text-[40px] text-center font-bold'>
            Based on a one-year period, you have missed out on approximately{' '}
            <span className='text-mainColor text-[50px]'>
              ${Math.floor(ExpectedPaybackCal(paybackFactor) * 365)}
            </span>{' '}
            that could have been paid back!
          </div>
          <ChartComponent
            payback={isFull ? ExpectedPaybackCal(paybackFactor) : 0}
          />
          <SubButton
            name='Start Receiving Payback'
            width='w-[300px]'
            style={`py-5`}
            onClick={() => {
              setCurrentPage(3);
            }}
          />
        </div>
      )}
    </>
  );
};
interface ChartComponentProps {
  payback: number;
}

const ChartComponent = ({ payback }: ChartComponentProps) => {
  const [chartData, setChartData] = useState<ChartData<'bar'>>({
    labels: [],
    datasets: [],
  });
  const [chartOptions, setChartOptions] = useState<ChartOptions>({});

  useEffect(() => {
    setChartData({
      labels: ['1 Day', '1 Week', '1 Month', '3 Months', '6 Months', '1 Year'],
      datasets: [
        {
          label: 'Expected Payback Amount',
          data: [
            payback,
            payback * 7,
            payback * 30,
            payback * 90,
            payback * 180,
            payback * 365,
          ],
          borderColor: '#f5f5f5',
          backgroundColor: '#1F4DED',
        },
      ],
    });

    setChartOptions({
      plugins: {
        legend: {
          position: 'top',
        },
      },
      maintainAspectRatio: false,
    });
  }, []);

  return (
    <div className='w-[800px] h-[500px] flex justify-center items-center'>
      <Bar
        data={chartData}
        options={chartOptions}
        style={{ width: '800px', height: '500px' }}
      />
    </div>
  );
};

const ThirdLandingPage = ({ setCurrentPage }: LandingPageProps) => {
  const { register, handleSubmit } = useForm();
  const onSubmit = () => {};

  const homeSectionStyle: string =
    'max-w-[1248px] mx-auto flex justify-center items-center';

  return (
    <div>
      <section className={`home-section1 ${homeSectionStyle} flex-col`}>
        <div className='section-title text-[40px] font-bold text-center px-6'>
          Get back your crypto trading fees with
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
              name='Apply for pre-registration'
              onClick={() => {}}
              style='py-[21px] px-[24px] w-[250px]'
              hoverBg={true}
            />
          </div>
        </form>
      </section>
    </div>
  );
};

const ExpectedPaybackCal = ({
  exchange,
  capital,
  leverage,
  frequencyType,
}: paybackFactorType) => {
  let difference: number;
  let frequency: number;

  if (exchange === 'binance') {
    difference = 0.000067;
  } else if (exchange === 'bybit') {
    difference = 0.000127;
  } else if (exchange === 'okx') {
    difference = 0.000068;
  } else if (exchange === 'bitget') {
    difference = 0.000094;
  } else {
    difference = 0.00016;
  }

  if (frequencyType === 1) {
    frequency = 0.67;
  } else if (frequencyType === 2) {
    frequency = 1.47;
  } else if (frequencyType === 3) {
    frequency = 3.97;
  } else if (frequencyType === 4) {
    frequency = 8.07;
  } else if (frequencyType === 5) {
    frequency = 21.27;
  } else if (frequencyType === 6) {
    frequency = 40.37;
  } else {
    frequency = 81.97;
  }

  return difference * capital * leverage * frequency;
};
