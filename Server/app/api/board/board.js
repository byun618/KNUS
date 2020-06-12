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