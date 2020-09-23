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