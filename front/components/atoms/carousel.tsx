import React, { useRef, useState } from "react";
// Import Swiper React components
import { Swiper, SwiperSlide } from "swiper/react";
import Image from "next/image";

// Import Swiper styles
import "swiper/css";
import "swiper/css/pagination";
import "swiper/css/navigation";

// import required modules
import { Pagination } from "swiper";

interface CarouselPropsType {
  carouselList: Array<CarouselListType>;
}

export interface CarouselListType {
  RC?: React.ReactElement;
  imageUrl?: string;
}

export const Carousel = ({ carouselList }: CarouselPropsType) => {
  return (
    <>
      <Swiper
        pagination={{ clickable: true }}
        modules={[Pagination]}
        className="mySwiper"
        loop={true}
      >
        {carouselList.map((el, idx) => (
          <SwiperSlide key={idx}>
            {el.RC ? (
              el.RC
            ) : (
              <Image
                src={el.imageUrl as string}
                alt="slide"
                width={10000}
                height={10000}
              />
            )}
          </SwiperSlide>
        ))}
      </Swiper>
    </>
  );
};
