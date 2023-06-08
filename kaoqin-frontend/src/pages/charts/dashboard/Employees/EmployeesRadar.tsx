import React, {useEffect, useState} from 'react';
import {Radar} from '@ant-design/plots';

const EmployeesRadar: React.FC = () => {
  const [data, setData] = useState([]);

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
    data: data.data.employees,
    xField: 'department',
    yField: 'employeeCount',
    meta: {
      employeeCount: {
        alias: '人数',
        min: 0,
        max: 10,
        range: [0, 1],
      },
    },
    xAxis: {
      line: null,
      tickLine: null,
      grid: {
        line: {
          style: {
            lineDash: null,
          },
        },
      },
    },
    yAxis: {
      line: null,
      tickLine: null,
      grid: {
        line: {
          type: 'line',
          style: {
            lineDash: null,
          },
        },
      },
    },
    // 开启面积
    area: {},
    // 开启辅助点
    point: {
      size: 2,
    },
  };

  return <Radar {...config} />;
};
export default EmployeesRadar;
