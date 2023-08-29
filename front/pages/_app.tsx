import "../styles/globals.css";
import { wrapper, persistor, makeStore } from "../ducks/store";
import { PersistGate } from "redux-persist/integration/react";
import { useEffect } from "react";
import type { NextPage } from "next";
import type { AppProps } from "next/app";
import type { ReactElement, ReactNode } from "react";
import DefaultLayout from "../layout/defaultLayout";
import { onSilentRefresh } from "../module/jsonWebToken";
import { getCookie } from "../module/cookies";
import { initLoginIdentity } from "../ducks/loginIdentitySlice";
import { LoadingIndicator } from "../components/atoms/loadingIndicator";
import { Provider } from "react-redux";

export type NextPageWithLayout<P = {}, IP = P> = NextPage<P, IP> & {
  getLayout?: (page: ReactElement) => ReactNode;
};
type AppPropsWithLayout = AppProps & {
  Component: NextPageWithLayout;
};

function App({ Component, pageProps }: AppPropsWithLayout) {
  const getLayout = Component.getLayout || ((page) => page);
  const store = makeStore(); // Store를 생성합니다.
  const dispatch = store.dispatch; // useDispatch 대신 store.dispatch를 사용합니다.

  useEffect(() => {
    if (getCookie("refreshJwtToken")) {
      onSilentRefresh();
    } else {
      dispatch(initLoginIdentity());
    }
  }, []);

  return (
    <Provider store={store}>
      <PersistGate persistor={persistor} loading={<LoadingIndicator />}>
        {Component.getLayout ? (
          getLayout(<Component {...pageProps} />)
        ) : (
          <DefaultLayout>
            {getLayout(<Component {...pageProps} />)}
          </DefaultLayout>
        )}
      </PersistGate>
    </Provider>
  );
}

export default wrapper.withRedux(App);
