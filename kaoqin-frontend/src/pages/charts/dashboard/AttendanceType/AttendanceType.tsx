import React, {useEffect, useState} from 'react';
import {Pie} from '@ant-design/plots';

const AttendanceTypePie: React.FC = () => {

  const [data, setData] = useState({});

  useEffect(() => {
    // eslint-disable-next-line @typescript-eslint/no-use-before-define
    asyncFetch();
  }, []);

  const asyncFetch = () => {
    fetch('http://localhost:8080/api/charts/getChartsData')
      .then((response) => response.json())
      .then((json) => setData(json))
      .catch((error) => {
        console.log('fetch data failed', error);
      });
  };
  if (!Object.keys(data).length) {
    return null;
  }
  const config = {
    appendPadding: 10,
    data: data.data.attendanceList,
    angleField: 'attendanceCount',
    colorField: 'attendanceType',
    radius: 0.75,
    label: {
      type: 'spider',
      labelHeight: 28,
      content: '{name}\n{percentage}',
    },
    interactions: [
      {
        type: 'element-selected',
      },
      {
        type: 'element-active',
      },
    ],
  };
  return <Pie {...config} />;
};
export default AttendanceTypePie;
