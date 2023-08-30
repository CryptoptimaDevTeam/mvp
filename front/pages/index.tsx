import { ReactElement } from 'react';
import NoLayout from '../layout/noLayout';
import Main from './main';
import PreRegister from './pre-register';

export default function Home() {
  return (
    <div>
      <PreRegister />
      {/* <Main /> */}
    </div>
  );
}

Home.getLayout = function getLayout(page: ReactElement) {
  return <NoLayout>{page}</NoLayout>;
};
