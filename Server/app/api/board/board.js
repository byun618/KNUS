const database = require('../../../data')
const moment = require('moment')

require('moment-timezone')
moment.tz.setDefault('Asia/Seoul')
    
exports.write = (req, res) => {
    console.log('write notice')
    var body = req.body
    
    var type = body.type
    var title = body.title
    var user_id = body.user_id
    var contents = body.contents
    var good = '0'
    var time = moment().format('YYYY-MM-DD HH:mm:ss')

    database.Board.create({
        type: type,
        title: title,
        contents: contents,
        user_id: user_id,
        good: good,
        time: time
    }).then( () => {
        console.log('successed to upload')
        res.status(200).json({ result : "Success" })
    }).catch( () => {
        console.log('failed to upload')
        res.status(404).json({ result: "Undefined error" })
    })
}

exports.load = (req, res) => {
    console.log('load notice')

    database.Board.findAll({
        where : {
            type : req.params.type
        }
    }).then( (result) =>{
        console.log('successed to load')
        res.status(200).json(result)
    }).catch( () => {
        console.log('failed to load')
        res.status(404).json({result : 'Undefined err'})
    })
}

exports.good = (req, res) => {
    console.log('good+1')
    //var board_id = req.body.id
  //  console.log(req.body)
    database.Board.findAll({
        where: {
            id: req.params.id
        }
    }).then((result) => {
        if (result[0].good != null) {
            var good = result[0].good;
            database.Board.update({
                good: good + 1
            },
                { where: { id: board_id } }
            ).then((result) => {
                res.status(404).json({ result: 'good' })
            }).catch(err => {
                console.error(err);
            });
        }
        else{
            console.log('board search failed')
            res.status(200).json({ result: 'no find' })
        }
    }).catch((err) => {
        console.log(err)
        res.status(404).json({ result: 'no' })
    })
    
}