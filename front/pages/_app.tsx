import '../styles/globals.css';
require('dotenv').config();
import { wrapper, persistor } from '../ducks/store';
import { PersistGate } from 'redux-persist/integration/react';
import type { ReactElement, ReactNode } from 'react';
import type { NextPage } from 'next';
import type { AppProps } from 'next/app';
import DefaultLayout from '../layout/defaultLayout';
import { LoadingIndicator } from '../components/atoms/loadingIndicator';
import { useEffect } from 'react';
import { onSilentRefresh } from '../module/jsonWebToken';
import { getCookie } from '../module/cookies';
import { useAppDispatch } from '../ducks/store';
import { initLoginIdentity } from '../ducks/loginIdentitySlice';
import useMaintainScroll from '../hooks/useMaintainScroll';

export type NextPageWithLayout<P = {}, IP = P> = NextPage<P, IP> & {
  getLayout?: (page: ReactElement) => ReactNode;
};

type AppPropsWithLayout = AppProps & {
  Component: NextPageWithLayout;
};
function MyApp({ Component, pageProps }: AppPropsWithLayout) {
  const getLayout = Component.getLayout || ((page) => page);
  const dispatch = useAppDispatch();
  useMaintainScroll();

  useEffect(() => {
    if (getCookie('refreshJwtToken')) {
      onSilentRefresh();
    } else {
      dispatch(initLoginIdentity());
    }
  }, []);

  return (
    <PersistGate persistor={persistor}>
      {Component.getLayout ? (
        getLayout(<Component {...pageProps} />)
      ) : (
        <DefaultLayout>{getLayout(<Component {...pageProps} />)}</DefaultLayout>
      )}
    </PersistGate>
  );
}

export default wrapper.withRedux(MyApp);
