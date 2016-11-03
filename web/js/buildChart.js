/**
 * Created by LuQP on 2016/9/19.
 */
function chart(id,leftTitle,rightTitle,leftColumn,rightColumn,dataProvider,valueF,valueS){
    var graphs = [ {
        "id": "g1",
        "valueAxis": "v1",
        "lineColor": "#62cf73",
        "fillColors": "#76B8F2",
        "fillAlphas": 1,
        "type": "column",
        "title": leftColumn,
        "valueField": valueF,
        "clustered": false,
        "columnWidth": 0.05,
        "legendValueText": "$[[value]]USD",
        "balloonText": "[[title]]<br /><b style='font-size: 130%'>$[[value]]USD</b>"
    }, {
        "id": "g2",
        "valueAxis": "v2",
        "bullet": "round",
        "bulletBorderAlpha": 1,
        "bulletColor": "#FFFFFF",
        "bulletSize": 5,
        "hideBulletsCount": 50,
        "lineThickness": 4,
        "lineColor": "#48474D",
        "type": "smoothedLine",
        "title": rightColumn,
        "useLineColorForBulletBorder": true,
        "valueField": valueS,
        "balloonText": "[[title]]<br /><b style='font-size: 130%'>[[value]]</b>"
    }];
    var chart = AmCharts.makeChart(id, {
        "type": "serial",
        "theme": "light",
        "dataDateFormat": "YYYY-MM-DD",
        "precision": 2,
        "valueAxes": [{
            "id": "v1",
            "title": leftTitle,
            "position": "left",
            "autoGridCount": false,
            "labelFunction": function(value) {
                return  " " + Math.round(value) + " USD";
            }
        }, {
            "id": "v2",
            "title": rightTitle,
            "gridAlpha": 0,
            "position": "right",
            "autoGridCount": false,
            "labelFunction": function(value) {
                return  Math.round(value) + " 个";
            }
        }],
        "graphs": graphs,
        /* "chartScrollbar": {
         "graph": "g1",
         "oppositeAxis": false,
         "offset": 30,
         "scrollbarHeight": 50,
         "backgroundAlpha": 0,
         "selectedBackgroundAlpha": 0.1,
         "selectedBackgroundColor": "#888888",
         "graphFillAlpha": 0,
         "graphLineAlpha": 0.5,
         "selectedGraphFillAlpha": 0,
         "selectedGraphLineAlpha": 1,
         "autoGridCount": true,
         "color": "#AAAAAA"
         },*/
        "chartCursor": {
            "pan": true,
            "valueLineEnabled": true,
            "valueLineBalloonEnabled": true,
            "cursorAlpha": 0,
            "valueLineAlpha": 0.2
        },
        "categoryField": "date",
        "categoryAxis": {
            "parseDates": true,
            "dashLength": 1,
            "minorGridEnabled": true
        },
        "legend": {
            "useGraphSettings": true,
            "position": "top"
        },
        "balloon": {
            "borderThickness": 1,
            "shadowAlpha": 0
        },
        "dataProvider":dataProvider
    });

};
function chart_t(id,leftTitle,dataProvider){
    var chart = AmCharts.makeChart(id, {
        "type": "serial",
        "theme": "light",
        "dataDateFormat": "YYYY-MM-DD",
        "precision": 2,
        "valueAxes": [{
            "id": "v1",
            "title": leftTitle,
            "position": "left",
            "autoGridCount": false,
            "labelFunction": function(value) {
                return  " " + Math.round(value) + " 个";
            }
        }],
        "graphs": [{
            "id": "g1",
            "valueAxis": "v1",
            "lineColor": "#62cf73",
            "fillColors": "#76B8F2",
            "fillAlphas": 1,
            "type": "column",
            "title": "支付订单数",
            "valueField": "pay",
            "clustered": false,
            "columnWidth": 0.05,
            "legendValueText": "$[[value]]USD",
            "balloonText": "[[title]]<br /><b style='font-size: 130%'>$[[value]]USD</b>"
        },{
            "id": "g2",
            "valueAxis": "v1",
            "bullet": "round",
            "bulletBorderAlpha": 1,
            "bulletColor": "#FFFFFF",
            "bulletSize": 5,
            "hideBulletsCount": 50,
            "lineThickness": 4,
            "lineColor": "#48474D",
            "type": "smoothedLine",
            "title": "下单订单数",
            "useLineColorForBulletBorder": true,
            "valueField": "orders",
            "balloonText": "[[title]]<br /><b style='font-size: 130%'>[[value]]</b>"
        },{
            "id": "g3",
            "valueAxis": "v1",
            "bullet": "round",
            "bulletBorderAlpha": 1,
            "bulletColor": "#FFFFFF",
            "bulletSize": 5,
            "hideBulletsCount": 50,
            "lineThickness": 4,
            "lineColor": "#93EE79",
            "type": "smoothedLine",
            "title": "加购物车人数",
            "useLineColorForBulletBorder": true,
            "valueField": "shopCar",
            "balloonText": "[[title]]<br /><b style='font-size: 130%'>[[value]]</b>"
        },{
            "id": "g4",
            "valueAxis": "v1",
            "bullet": "round",
            "bulletBorderAlpha": 1,
            "bulletColor": "#FFFFFF",
            "bulletSize": 5,
            "hideBulletsCount": 50,
            "lineThickness": 4,
            "lineColor": "#F8A457",
            "type": "smoothedLine",
            "title": "加收藏夹人数",
            "useLineColorForBulletBorder": true,
            "valueField": "collect",
            "balloonText": "[[title]]<br /><b style='font-size: 130%'>[[value]]</b>"
        }],
        /* "chartScrollbar": {
         "graph": "g1",
         "oppositeAxis": false,
         "offset": 30,
         "scrollbarHeight": 50,
         "backgroundAlpha": 0,
         "selectedBackgroundAlpha": 0.1,
         "selectedBackgroundColor": "#888888",
         "graphFillAlpha": 0,
         "graphLineAlpha": 0.5,
         "selectedGraphFillAlpha": 0,
         "selectedGraphLineAlpha": 1,
         "autoGridCount": true,
         "color": "#AAAAAA"
         },*/
        "chartCursor": {
            "pan": true,
            "valueLineEnabled": true,
            "valueLineBalloonEnabled": true,
            "cursorAlpha": 0,
            "valueLineAlpha": 0.2
        },
        "categoryField": "date",
        "categoryAxis": {
            "parseDates": true,
            "dashLength": 1,
            "minorGridEnabled": true
        },
        "legend": {
            "useGraphSettings": true,
            "position": "top"
        },
        "balloon": {
            "borderThickness": 1,
            "shadowAlpha": 0
        },
        "dataProvider":dataProvider
    });
};

function chart4(id,leftTitle,rightTitle,leftColumn,rightColumn,dataProvider,valueF,valueS){
    var graphs = [ {
        "id": "g1",
        "valueAxis": "v1",
        "lineColor": "#62cf73",
        "fillColors": "#76B8F2",
        "fillAlphas": 1,
        "type": "column",
        "title": leftColumn,
        "valueField": valueF,
        "clustered": false,
        "columnWidth": 0.05,
        "legendValueText": "$[[value]]USD",
        "balloonText": "[[title]]<br /><b style='font-size: 130%'>$[[value]]USD</b>"
    }, {
        "id": "g2",
        "valueAxis": "v2",
        "bullet": "round",
        "bulletBorderAlpha": 1,
        "bulletColor": "#FFFFFF",
        "bulletSize": 5,
        "hideBulletsCount": 50,
        "lineThickness": 4,
        "lineColor": "#48474D",
        "type": "smoothedLine",
        "title": rightColumn,
        "useLineColorForBulletBorder": true,
        "valueField": valueS,
        "balloonText": "[[title]]<br /><b style='font-size: 130%'>[[value]]</b>"
    }];
    var chart = AmCharts.makeChart(id, {
        "type": "serial",
        "theme": "light",
        "dataDateFormat": "YYYY-MM-DD",
        "precision": 2,
        "valueAxes": [{
            "id": "v1",
            "title": leftTitle,
            "position": "left",
            "autoGridCount": false,
            "labelFunction": function(value) {
                return  " " + Math.round(value) + " 个";
            }
        }, {
            "id": "v2",
            "title": rightTitle,
            "gridAlpha": 0,
            "position": "right",
            "autoGridCount": false,
            "labelFunction": function(value) {
                return  Math.round(value) + " %";
            }
        }],
        "graphs": graphs,
        "chartCursor": {
            "pan": true,
            "valueLineEnabled": true,
            "valueLineBalloonEnabled": true,
            "cursorAlpha": 0,
            "valueLineAlpha": 0.2
        },
        "categoryField": "date",
        "categoryAxis": {
            "parseDates": true,
            "dashLength": 1,
            "minorGridEnabled": true
        },
        "legend": {
            "useGraphSettings": true,
            "position": "top"
        },
        "balloon": {
            "borderThickness": 1,
            "shadowAlpha": 0
        },
        "dataProvider":dataProvider
    });

};
/*
[{
    "date": "2013-01-16",
    "market1": 71,
    "market2": 75,
    "sales1": 5,
    "sales2": 8
}, {
    "date": "2013-01-17",
    "market1": 74,
    "market2": 78,
    "sales1": 4,
    "sales2": 6
}, {
    "date": "2013-01-18",
    "market1": 78,
    "market2": 88,
    "sales1": 5,
    "sales2": 2
}, {
    "date": "2013-01-19",
    "market1": 85,
    "market2": 89,
    "sales1": 8,
    "sales2": 9
}, {
    "date": "2013-01-20",
    "market1": 82,
    "market2": 89,
    "sales1": 9,
    "sales2": 6
}, {
    "date": "2013-01-21",
    "market1": 83,
    "market2": 85,
    "sales1": 3,
    "sales2": 5
}, {
    "date": "2013-01-22",
    "market1": 88,
    "market2": 92,
    "sales1": 5,
    "sales2": 7
}, {
    "date": "2013-01-23",
    "market1": 85,
    "market2": 90,
    "sales1": 7,
    "sales2": 6
}, {
    "date": "2013-01-24",
    "market1": 85,
    "market2": 91,
    "sales1": 9,
    "sales2": 5
}, {
    "date": "2013-01-25",
    "market1": 80,
    "market2": 84,
    "sales1": 5,
    "sales2": 8
}, {
    "date": "2013-01-26",
    "market1": 87,
    "market2": 92,
    "sales1": 4,
    "sales2": 8
}, {
    "date": "2013-01-27",
    "market1": 84,
    "market2": 87,
    "sales1": 3,
    "sales2": 4
}, {
    "date": "2013-01-28",
    "market1": 83,
    "market2": 88,
    "sales1": 5,
    "sales2": 7
}, {
    "date": "2013-01-29",
    "market1": 84,
    "market2": 87,
    "sales1": 5,
    "sales2": 8
}, {
    "date": "2013-01-30",
    "market1": 81,
    "market2": 85,
    "sales1": 4,
    "sales2": 7
}]*/
