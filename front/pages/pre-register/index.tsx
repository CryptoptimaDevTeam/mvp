import { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import ReactTyped from 'react-typed';
import type { preRegisterFormType } from '../../module/preRegister';
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
import { postPreRegister } from '../../module/preRegister';

const PreRegister = () => {
  const [currentPage, setCurrentPage] = useState<number>(1);

  return (
    <div className='landing-page min-h-[100vh] flex flex-col justify-between items-center'>
      <LandingPageHeader />
      <main className='w-full flex justify-center items-center'>
        {currentPage === 1 && (
          <FirstLandingPage setCurrentPage={setCurrentPage} />
        )}
        {currentPage === 2 && (
          <SecondLandingPage setCurrentPage={setCurrentPage} />
        )}
        {currentPage === 3 && (
          <ThirdLandingPage setCurrentPage={setCurrentPage} />
        )}
        {currentPage === 4 && <FourthLandingPage />}
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
        className='text-[32px] text-center font-bold'
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
          width='w-[300px]'
          style={`py-5 font-medium ${
            showButton ? 'cursor-pointer' : 'cursor-default'
          }`}
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

  const questionStyle: string = `text-[16px] text-center font-medium`;
  return (
    <>
      {currentSubPage === 1 && (
        <section className='w-full px-5 flex flex-col justify-start gap-10 py-10'>
          <ReactTyped
            className='text-[32px] text-center font-bold'
            strings={[
              "Shall we find out how much money you've missed out on so far?",
            ]}
            typeSpeed={30}
          />
          <div
            className={`flex flex-col justify-center items-center transition-opacity duration-500 p-[40px] border-2 border-borderColor rounded-xl ${
              showButton ? 'opacity-100' : 'opacity-0'
            } gap-[60px] max-w-[1200px] mx-auto`}
          >
            <>
              <div className='exchange-select flex flex-col gap-5'>
                <div className={`${questionStyle}`}>
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
                        width={150}
                        height={150}
                      />
                    </div>
                  ))}
                </div>
                <div className='flex justify-center'>
                  <input
                    type='checkbox'
                    id='etc'
                    onChange={() => {
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
                <label className={`${questionStyle}`} htmlFor='capital'>
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
                <label className={`${questionStyle}`} htmlFor='capital'>
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
                <label className={`${questionStyle}`} htmlFor='frequency'>
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
                      <option key={el} value={idx + 1}>
                        {el}
                      </option>
                    ))}
                  </select>
                </div>
              </div>

              <MainButton
                name='Check Estimated Payback Amount'
                width='w-[300px]'
                style={`py-5 font-medium ${
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
        <section className='w-full h-full flex flex-col justify-center items-center gap-10'>
          <div className='w-full h-full text-[32px] text-center font-bold'>
            Calculating the estimated payback amount through AI.
          </div>
          <div>
            <LoadingIndicator />
          </div>
        </section>
      )}

      {currentSubPage === 3 && (
        <div className='w-full h-full flex flex-col justify-center items-center gap-10 py-20'>
          <div className='w-[1080px] text-[32px] text-center font-bold'>
            Based on a one-year period, you have missed out on approximately{' '}
            <span className='text-mainColor text-[50px]'>
              ${Math.floor(ExpectedPaybackCal(paybackFactor) * 365)}
            </span>{' '}
            that could have been paid back!
          </div>
          <ChartComponent
            payback={isFull ? ExpectedPaybackCal(paybackFactor) : 0}
          />
          <div className='pt-10'>
            <MainButton
              name='Start Receiving Payback'
              width='w-[240px]'
              style={`py-5 font-medium`}
              onClick={() => {
                setCurrentPage(3);
              }}
            />
          </div>
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
          backgroundColor: '#6574A9',
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
  const { register, handleSubmit } = useForm<preRegisterFormType>();
  const onSubmitHandle = (data: preRegisterFormType) => {
    console.log(data);
    postPreRegister(data);
    // setCurrentPage(4)
  };

  const homeSectionStyle: string =
    'max-w-[1248px] mx-auto flex justify-center items-center';

  return (
    <>
      <section className={`${homeSectionStyle} flex-col py-10 px-5 mx-auto`}>
        <div className='section-title'>
          <div className='text-[32px] max-w-[800px] font-bold text-center'>
            Get back your crypto trading fees with Cryptoptima's Payback Service
          </div>
          <div className='text-[16px] text-mainColor font-semibold pt-2.5 text-center'>
            maximum trading fee discounts + up to 99% referral bonus payback
          </div>
        </div>
        <div className='section-form'>
          <div className='section-body pt-10 text-[16px] text-center pb-5 font-medium'>
            Our service is soon to be launched. Pre-register now, and we'll
            notify you of the release via email!
          </div>
          <form
            onSubmit={handleSubmit(onSubmitHandle)}
            className='p-[40px] border-2 border-borderColor rounded-xl flex flex-col gap-5'
          >
            <div className='email-input flex flex-col gap-2.5'>
              <label htmlFor='email'>1. What is your email address?</label>
              <input
                type='text'
                id='email'
                className='border-[1px] border-borderColor rounded w-full h-[50px] p-2.5'
                placeholder='cryptoptima@gmail.com'
                {...register('email')}
              />
            </div>
            <div className='exchange-input flex flex-col gap-2.5'>
              <label htmlFor='exchange'>
                2. Which crypto exchanges do you primarily use?
              </label>
              <select
                id='exchange'
                defaultValue='none'
                className='border-[1px] border-borderColor rounded w-[200px] h-[50px] p-2.5'
                {...register('exchange')}
              >
                <option value='none' disabled>
                  Choose an exchange
                </option>
                <option value='binance'>Binance</option>
                <option value='bybit'>Bybit</option>
                <option value='okx'>OKX</option>
                <option value='bitget'>Bitget</option>
                <option value='kucoin'>KuCoin</option>
                <option value='deribit'>Deribit</option>
                <option value='lbank'>LBank</option>
                <option value='weex'>WEEX</option>
                <option value='bitfinex'>Bitfinex</option>
                <option value='bitrue'>Bitrue</option>
                <option value='kraken'>Kraken</option>
                <option value='mexc'>MEXC</option>
                <option value='bitmex'>BitMEX</option>
                <option value='huobi'>Huobi</option>
                <option value='gateio'>Gate.io</option>
                <option value='coinw'>CoinW</option>
                <option value='zoomex'>ZOOMEX</option>
                <option value='bingx'>BingX</option>
                <option value='bixmart'>BitMart</option>
                <option value='others'>Any other exchanges</option>
              </select>
            </div>
            <div className='type-input flex flex-col gap-2.5'>
              <label>
                {`3. Do you mainly trade spot or derivatives(futures)?`}
              </label>
              <div className='flex gap-2.5'>
                <input
                  {...register('type')}
                  type='radio'
                  id='spot'
                  value='spot'
                />
                <label htmlFor='spot'>Spot</label>

                <input
                  {...register('type')}
                  type='radio'
                  id='derivatives'
                  value='derivatives'
                />
                <label htmlFor='derivatives'>Derivatives</label>
              </div>
            </div>

            <div className='w-full register-btn mt-5 flex justify-center'>
              <MainButton
                name='Apply for pre-registration'
                type='submit'
                width='w-[240px]'
                style='py-[21px] px-[24px] font-medium'
                hoverBg={true}
              />
            </div>
          </form>
        </div>
      </section>
    </>
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

const FourthLandingPage = () => {
  return (
    <div className='flex flex-col px-5 mx-auto justify-center items-center'>
      <div className='text-[32px] font-bold text-center'>
        Thank you for pre-registering!
      </div>
      <div className='text-[32px] font-bold text-center'>
        We'll strive to launch the service as soon as possible.
      </div>
    </div>
  );
};
