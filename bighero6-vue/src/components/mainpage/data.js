export default function getCalendarData(d) {
  const date = d ? new Date(d) : new Date();
  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDate();


  const ArrayData = [
    {
      date: `2020-09-20`,
      title: 'BTS-DNA',
      weather:'sun',
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg'
    },
    {
      date: `${year}-${month}-${day}`,
      title: '헤이즈-비도오고그래서',
      weather:'rain',
      album:'https://cdnimg.melon.co.kr/cm/album/images/100/74/454/10074454_500.jpg'
    },
    {
      date: `${year}-${month}-${day+1%30}`,
      title: 'DuaLipa-Dontstart now',
      weather:'rain',
      album:'https://cdnimg.melon.co.kr/cm2/album/images/103/45/880/10345880_20200326181909_500.jpg',
    },{
      date: `${year}-${month + 1}-15`,
      title: 'music festival',
      weather:'cloudy',
      album:'https://cdnimg.melon.co.kr/cm/album/images/100/74/454/10074454_500.jpg'
    },
    {
      date: `${year}-${month + 2}-6`,
      title: 'a course of lectures',
      weather:'cloud',
      album:'https://cdnimg.melon.co.kr/cm/album/images/100/74/454/10074454_500.jpg'
    }
  ];

  [{
    date: `2020-09-20`,
    title: 'BTS-DNA',
    weather:'sunny (1)',
    feel:'happy',
    song : {
        songId: '10197480',
        title: 'DNA',
        singer: 'BTS'
    },
    albumCover:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    context:'친구랑 같이 노래방 간날',
    img:'https://picsum.photos/510/300?1'
  },
  {
    date: `2020-09-21`,
    title: 'BTS-DNA',
    weather:'umbrella (1)',
    emotion:'meh',
    music : {
        musicID: '10074454',
        title: '비도오고그래서',
        singer: '헤이즈'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'오늘은 비가 너무 많이 왔다.',
    contentImg:'https://picsum.photos/510/300?2'
  },{
    date: `2020-09-22`,
    title: 'BTS-DNA',
    weather:'sunny (1)',
    emotion:'happy',
     music : {
        musicID: '10197480',
        title: 'DNA',
        singer: 'BTS'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'오늘은 날이 너무 좋다. 노래방 가고싶다 ㅎㅎㅎ',
    contentImg:'https://picsum.photos/510/300?3'
  },
   {
    date: `2020-09-14`,
    title: '트와이스-cheerup',
    weather:'cloud (1)',
    emotion:'smile',
    music : {
        musicID: '10238683',
        title: '벌써12시',
        singer: '청하'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다',
    contentImg:'https://picsum.photos/510/300?4'
  },
  {
    date: `2020-09-11`,
    title: '여행-볼빨간사춘기',
    weather:'windy (1)',
    emotion:'meh',
    music : {
        musicID: '10170069',
        title: '여행',
        singer: '볼빨간 사춘기'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'해외든 국내든 여행가고싶다ㅜㅜ 노래로 대신 여행기분 느껴야지',
    contentImg:'https://picsum.photos/510/300?5'
  },
  {
    date: `2020-09-02`,
    title: '마마무-데칼코마니',
    weather:'storm (1)',
    emotion:'sad',
    music : {
        musicID: '10012900',
        title: '데칼코마니',
        singer: '마마무'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'벌써 가을이다. 저녁되면 쌀쌀해지는듯 ㅜㅜ',
    contentImg:'https://picsum.photos/510/300?5'
  },
  {
    date: `2020-09-17`,
    title: '트와이스-cheerup',
    weather:'storm (1)',
    emotion:'smile',
    music : {
        musicID: '10096855',
        title: '가을아침',
        singer: '아이유'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다',
    contentImg:'https://picsum.photos/510/300?6'
  },
  {
    date: `2020-09-18`,
    title: '트와이스-cheerup',
    weather:'storm (1)',
    emotion:'smile',
    music : {
        musicID: '10001952',
        title: '숨',
        singer: '박효신'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'오늘 구름은 많이 꼈지만 친구만나서 기분 좋아짐 ;)',
    contentImg:'https://picsum.photos/510/300?6'
  },
  {
    date: `2020-09-08`,
    title: '트와이스-cheerup',
    weather:'umbrella (1)',
    emotion:'smile',
    music : {
        musicID: '10001952',
        title: '숨',
        singer: '박효신'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'오늘 아무것도 안해서 심심했다.',
    contentImg:'https://picsum.photos/510/300?6'
  },
  {
    date: `2020-09-09`,
    title: '트와이스-cheerup',
    weather:'umbrella (1)',
    emotion:'smile',
    music : {
        musicID: '10217134',
        title: '너를 만나',
        singer: '폴킴'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다',
    contentImg:'https://picsum.photos/510/300?6'
  },
  {
    date: `2020-08-21`,
    title: 'BTS-DNA',
    weather:'sunny (1)',
    emotion:'happy',
    music : {
        musicID: '10197480',
        title: 'DNA',
        singer: 'BTS'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'친구랑 같이 노래방 간날',
    contentImg:'https://picsum.photos/510/300?1'
  },
  {
    date: `2020-08-22`,
    title: 'BTS-DNA',
    weather:'umbrella (1)',
    emotion:'meh',
    music : {
        musicID: '10074454',
        title: '비도오고그래서',
        singer: '헤이즈'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'오늘은 비가 너무 많이 왔다.',
    contentImg:'https://picsum.photos/510/300?2'
  },{
    date: `2020-08-01`,
    title: 'BTS-DNA',
    weather:'sunny (1)',
    emotion:'happy',
     music : {
        musicID: '10197480',
        title: 'DNA',
        singer: 'BTS'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'오늘은 날이 너무 좋다. 노래방 가고싶다 ㅎㅎㅎ',
    contentImg:'https://picsum.photos/510/300?3'
  },
   {
    date: `2020-08-15`,
    title: '트와이스-cheerup',
    weather:'cloud (1)',
    emotion:'smile',
    music : {
        musicID: '10238683',
        title: '벌써12시',
        singer: '청하'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다',
    contentImg:'https://picsum.photos/510/300?4'
  },
  {
    date: `2020-08-12`,
    title: '여행-볼빨간사춘기',
    weather:'windy (1)',
    emotion:'meh',
    music : {
        musicID: '10170069',
        title: '여행',
        singer: '볼빨간 사춘기'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'해외든 국내든 여행가고싶다ㅜㅜ 노래로 대신 여행기분 느껴야지',
    contentImg:'https://picsum.photos/510/300?5'
  },
  {
    date: `2020-08-02`,
    title: '마마무-데칼코마니',
    weather:'storm (1)',
    emotion:'sad',
    music : {
        musicID: '10012900',
        title: '데칼코마니',
        singer: '마마무'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'벌써 가을이다. 저녁되면 쌀쌀해지는듯 ㅜㅜ',
    contentImg:'https://picsum.photos/510/300?5'
  },
  {
    date: `2020-08-17`,
    title: '트와이스-cheerup',
    weather:'storm (1)',
    emotion:'smile',
    music : {
        musicID: '10096855',
        title: '가을아침',
        singer: '아이유'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다',
    contentImg:'https://picsum.photos/510/300?6'
  },
  {
    date: `2020-08-30`,
    title: '트와이스-cheerup',
    weather:'storm (1)',
    emotion:'smile',
    music : {
        musicID: '10001952',
        title: '숨',
        singer: '박효신'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'오늘 구름은 많이 꼈지만 친구만나서 기분 좋아짐 ;)',
    contentImg:'https://picsum.photos/510/300?6'
  },
  {
    date: `2020-08-05`,
    title: '트와이스-cheerup',
    weather:'umbrella (1)',
    emotion:'smile',
    music : {
        musicID: '10001952',
        title: '숨',
        singer: '박효신'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'오늘 아무것도 안해서 심심했다.',
    contentImg:'https://picsum.photos/510/300?6'
  },
  {
    date: `2020-08-09`,
    title: '트와이스-cheerup',
    weather:'windy (1)',
    emotion:'smile',
    music : {
        musicID: '10217134',
        title: '너를 만나',
        singer: '폴킴'
    },
    album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
    content:'오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다',
    contentImg:'https://picsum.photos/510/300?6'
  },
  ]

  /* {
      date: `${year}-${month}-${day}`,
      title: 'shopping'
    }, */

/*   const ObjectData = {};

  ArrayData.forEach((item) => {
    ObjectData[item.date] = { ...item };
  }); */

  return {
    Array: ArrayData,
    /* Object: ObjectData */
  };
}

// export default {
//   Array: ArrayData,
//   Object: ObjectData
// };