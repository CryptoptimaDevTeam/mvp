import axios, { AxiosError } from 'axios';

export interface preRegisterFormType {
  email: string;
  exchange: string;
  type: string;
}

export async function postPreRegister({
  email,
  exchange,
  type,
}: preRegisterFormType) {
  try {
    const response = await axios
      .post(`${process.env.NEXT_PUBLIC_SERVER_URL}/survey`, {
        email,
        exchange,
        type,
      })
      .then((res) => res.status);
  } catch (e) {
    if (e instanceof AxiosError) {
      return e.response?.status;
    }
  }
}
